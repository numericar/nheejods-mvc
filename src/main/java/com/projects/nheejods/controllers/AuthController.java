package com.projects.nheejods.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.nheejods.dtos.RegisterDto;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/auths")
public class AuthController {

    @GetMapping("/login")
    public String viewLoginPage() {
        return "auths/login";
    }

    @GetMapping("/register")
    public String viewRegisterPage(Model model) {
        RegisterDto registerDto = new RegisterDto();

        model.addAttribute("registerDto", registerDto);

        return "auths/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerDto") RegisterDto registerDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "auths/register";
        }

        System.out.println(registerDto.toString());

        return "redirect:/auths/login";
    }
}
