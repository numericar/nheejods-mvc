package com.projects.nheejods.controllers;

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

import com.projects.nheejods.dtos.boxs.BoxDto;
import com.projects.nheejods.dtos.boxs.BoxItemDto;

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
    	model.addAttribute("months", this.MONTHS);
    	model.addAttribute("years", this.YEARS);
    	
        return "boxs/boxs_create";
    }
    
    @PostMapping("/create")
    public String createBox(Model model, @ModelAttribute BoxDto boxDto, BindingResult result) {
    	
    	if (result.hasErrors()) {
    		System.out.println("Has errors");
    		return "boxs/boxs_create";
    	}
    	
    	if (boxDto.getMonth().isPresent()) {
    		model.addAttribute("monthSelected", boxDto.getMonth().get());
    	}
    	
    	if (boxDto.getYear().isPresent()) {
    		model.addAttribute("yearSelected", boxDto.getYear().get());
    	}
    	
    	System.out.println("Month: " + boxDto.getMonth().get());
    	System.out.println("Year: " + boxDto.getYear().get());
    	
    	System.out.println("Incomes:");
    	for (BoxItemDto income : boxDto.getIncomes()) {
    		System.out.println(income.toString());
    	}
    	System.out.println();
    	
    	
    	System.out.println("Expenses:");
    	for (BoxItemDto expense : boxDto.getExpenses()) {
    		System.out.println(expense.toString());
    	}
    	System.out.println();
    	
    	
    	return "boxs/boxs_create";
    }
}








