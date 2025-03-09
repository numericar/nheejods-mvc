package com.projects.nheejods.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projects.nheejods.dtos.boxs.BoxDto;
import com.projects.nheejods.dtos.boxs.BoxItemDto;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/boxs")
public class BoxController {

    private final List<String> MONTHS;
    private final List<Integer> YEARS;

    public BoxController() {
        this.MONTHS = List.of(
            "January", 
            "February", 
            "March", 
            "April", 
            "May", 
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        );

        this.YEARS = List.of(2025, 2026, 2027, 2028);
    }

    @GetMapping
    public String viewBoxsPage(
            Model model,
            @RequestParam(name = "months", required = false) Optional<String> month,
            @RequestParam(name = "years", required = false) Optional<Integer> year) {
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
    	for (BoxItemDto income : boxDto.getIncomes()) {
    		System.out.println(income.toString());
    	}
    	
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








