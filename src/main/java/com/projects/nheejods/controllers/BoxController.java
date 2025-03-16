package com.projects.nheejods.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.userdetails.UserDetails;

import com.projects.nheejods.dtos.boxs.BoxDto;
import com.projects.nheejods.dtos.boxs.BoxItemDto;
import com.projects.nheejods.dtos.boxs.ShortBoxDto;
import com.projects.nheejods.entities.Box;
import com.projects.nheejods.entities.BoxItem;
import com.projects.nheejods.entities.User;
import com.projects.nheejods.enums.BoxItemType;
import com.projects.nheejods.services.interfaces.BoxService;
import com.projects.nheejods.services.interfaces.MonthService;
import com.projects.nheejods.services.interfaces.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/boxs")
public class BoxController {

    private final String[] MONTHS;
    private final int[] YEARS;
    
    private final MonthService monthService;
    private final UserService userService;
    private final BoxService boxService;

    public BoxController(MonthService monthService, UserService userService, BoxService boxService) {
        this.MONTHS = monthService.getNameMonths();
        this.YEARS = new int[] { 2025, 2026, 2027, 2028 };
        
        this.monthService = monthService;
        this.userService = userService;
        this.boxService = boxService;
    }

    @GetMapping
    public String viewBoxsPage(
            Model model,
            @RequestParam(name = "months", required = false) Optional<String> month,
            @RequestParam(name = "years", required = false) Optional<Integer> year) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	UserDetails userDetail = (UserDetails) authentication.getPrincipal();
    	Optional<User> userOptional = this.userService.getUserByEmail(userDetail.getUsername());
    	
    	if (userOptional.isEmpty()) {
    		return "redirect:/auths/login";
    	}
    	
        model.addAttribute("months", this.MONTHS);
        model.addAttribute("years", this.YEARS);

        if (month.isPresent()) {
            model.addAttribute("monthSelected", month.get());
        } else {
            model.addAttribute("monthSelected", null);
        }

        if (year.isPresent()) {
            model.addAttribute("yearSelected", year.get());
        } else {
            model.addAttribute("yearSelected", null);
        }
        
        // read user boxs
        Optional<List<Box>> boxsOptional = this.boxService.getBoxByUser(userOptional.get());

        List<ShortBoxDto> shortBoxDtos = new ArrayList<>();
        
        if (boxsOptional.isPresent()) 
        {
        	for (Box box : boxsOptional.get()) {
        		// read box items
        		List<BoxItem> boxItems = box.getBoxItems();
        		
                double incomeSummary = 0.0;
                double expenseSummary = 0.0;
                double remainingSummary = 0.0;
                double expensePercent = 0.0;

        		// summary income
                for (BoxItem boxItem : boxItems) { 
                    if (boxItem.getItemType() == BoxItemType.INCOME) {
                        incomeSummary += boxItem.getAmount();
                    }

                    if (boxItem.getItemType() == BoxItemType.EXPENSE) {
                        expenseSummary += boxItem.getAmount();
                    }
                }

                remainingSummary = incomeSummary - expenseSummary;

                if (incomeSummary <= 0) {
                    expensePercent = 100.0;
                } else {
                    expensePercent = (expensePercent / incomeSummary) * 100.0;
                }

                String monthName = this.monthService.getMonthName(box.getMonth());
                ShortBoxDto shortBoxDto = new ShortBoxDto(monthName, box.getYear(), incomeSummary, expenseSummary, remainingSummary, expensePercent);
                
                shortBoxDtos.add(shortBoxDto);
        	}
        }
        
        model.addAttribute("boxs", shortBoxDtos);

        return "boxs/boxs_index";
    }

    @GetMapping("/create")
    public String viewCreateBoxPage(Model model) {
    	Optional<List<BoxItemDto>> incomesOptional = model.containsAttribute("incomes") ? this.safeCastList(model.getAttribute("incomes")) : Optional.empty();
    	Optional<List<BoxItemDto>> expensesOptional = model.containsAttribute("expenses") ? this.safeCastList(model.getAttribute("expenses")) : Optional.empty();
    	
        List<BoxItemDto> incomes;
        List<BoxItemDto> expenses;
        
        if (incomesOptional.isPresent()) {
        	incomes = incomesOptional.get();
        } else {
        	incomes = new ArrayList<>();
        }
        
        if (expensesOptional.isPresent()) {
        	expenses = expensesOptional.get();
        } else {
        	expenses = new ArrayList<>();
        }
        
        double incomeSummary = 0;
        for (BoxItemDto income : incomes) {
        	incomeSummary += income.getAmount();
        }
        
        double expenseSummary = 0;
        for (BoxItemDto expense : expenses) {
        	expenseSummary += expense.getAmount();
        }
        
        double remainingSummary = incomeSummary - expenseSummary;
        
    	model.addAttribute("months", this.MONTHS);
    	model.addAttribute("years", this.YEARS);
    	model.addAttribute("incomes", incomes);
        model.addAttribute("expenses",expenses);
        model.addAttribute("incomeSummary", incomeSummary);
        model.addAttribute("expenseSummary", expenseSummary);
        model.addAttribute("remainingSummary", remainingSummary);
        model.addAttribute("currentIncomeCount", incomes.size());
        model.addAttribute("currentExpenseCount", expenses.size());
    	
        return "boxs/boxs_create";
    }
    
    @PostMapping("/create")
    public String createBox(
    		Model model, 
    		@Valid @ModelAttribute BoxDto boxDto, 
    		BindingResult result,
    		RedirectAttributes redirectAttributes) {
    	
    	if (result.hasErrors()) {
        	if (boxDto.getYear() != null && boxDto.getYear().isPresent()) {
        		redirectAttributes.addFlashAttribute("yearSelected", boxDto.getYear().get());
        	}
        	
        	if (boxDto.getMonth() != null && boxDto.getMonth().isPresent()) {
        		redirectAttributes.addFlashAttribute("monthSelected", boxDto.getMonth().get());
        	}
        	
        	redirectAttributes.addFlashAttribute("incomes", boxDto.getIncomes());
        	redirectAttributes.addFlashAttribute("expenses", boxDto.getExpenses());
        	
    		return "redirect:/boxs/create";
    	}
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	UserDetails userDetail = (UserDetails) authentication.getPrincipal();
    	Optional<User> userOptional = this.userService.getUserByEmail(userDetail.getUsername());
    	
    	if (userOptional.isEmpty()) {
    		return "redirect:/auths/login";
    	}
    	
    	// convert month name to index of month
    	int monthIndex = this.monthService.getMonthIndex(boxDto.getMonth().get());
    	
    	// validate box is created by current user
    	boolean isExist = this.boxService.isExist(monthIndex, boxDto.getYear().get(), userOptional.get().getId());
    	if (isExist) {
    		if (boxDto.getYear() != null && boxDto.getYear().isPresent()) {
        		redirectAttributes.addFlashAttribute("yearSelected", boxDto.getYear().get());
        	}
        	
        	if (boxDto.getMonth() != null && boxDto.getMonth().isPresent()) {
        		redirectAttributes.addFlashAttribute("monthSelected", boxDto.getMonth().get());
        	}
        	
        	redirectAttributes.addFlashAttribute("incomes", boxDto.getIncomes());
        	redirectAttributes.addFlashAttribute("expenses", boxDto.getExpenses());
        	
    		return "redirect:/boxs/create";
    	}
    	
    	// create box
        Box boxCreated = this.boxService.createBox(boxDto.getYear().get(), monthIndex, userOptional.get());

        // append income to box
        for (BoxItemDto income : boxDto.getIncomes()) {
        	this.boxService.appendIncomeItem(boxCreated, income.getTitle(), income.getAmount());
        }
        
        // append expense to box
        for (BoxItemDto expense : boxDto.getExpenses()) {
        	this.boxService.appendIncomeItem(boxCreated, expense.getTitle(), expense.getAmount());
        }
    	
    	return "redirect:/boxs/create";
    }
    
    @SuppressWarnings("unchecked")
    private Optional<List<BoxItemDto>> safeCastList(Object obj) {
        if (obj instanceof List<?>) {
            List<?> list = (List<?>) obj;
            if (list.isEmpty() || list.get(0) instanceof BoxItemDto) {
                return Optional.of((List<BoxItemDto>) list);
            }
        }
        return Optional.empty();
    }
}








