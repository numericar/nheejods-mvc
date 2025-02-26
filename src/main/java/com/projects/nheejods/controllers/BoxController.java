package com.projects.nheejods.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boxs")
public class BoxController {

    @GetMapping
    public String viewBoxsPage() {
        return "boxs/boxs_index";
    }

}
