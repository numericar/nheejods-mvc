package com.projects.nheejods.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
