package com.projects.nheejods.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.nheejods.dtos.RegisterDto;
import com.projects.nheejods.entities.Role;
import com.projects.nheejods.services.interfaces.RoleService;
import com.projects.nheejods.services.interfaces.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/auths")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;
    
    public AuthController(PasswordEncoder passwordEncoder, UserService userService, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
    }

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
        try {
            if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
                result.rejectValue("confirmPassword", "error.registerDto", "Password and Confirm password not matched");
            }
    
            if (this.userService.isDupplicateEmail(registerDto.getEmail())) {
                result.rejectValue("email", "error.registerDto", "Email is aleady to use");
            }
    
            if (result.hasErrors()) {
                return "auths/register";
            }
    
            String passwordEncoded = this.passwordEncoder.encode(registerDto.getPassword());
    
            Role memberRole = this.roleService.getMemberRole();
    
            this.userService.create(registerDto.getEmail(), passwordEncoded, memberRole);
    
            return "redirect:/auths/login";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

            return "auths/register";
        }
    }
}
