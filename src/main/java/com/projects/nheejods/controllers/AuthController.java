package com.projects.nheejods.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auths")
public class AuthController {

    @GetMapping("/login")
    public String viewLoginPage() {
        return "auths/login";
    }

    @GetMapping("/register")
    public String viewRegisterPage() {
        return "auths/register";
    }

}
