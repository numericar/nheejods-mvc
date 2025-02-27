package com.projects.nheejods.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterDto {

    @NotBlank(message = "Email should not be blank")
    @Size(min = 6, max = 50, message = "Email should between 6 and 50 characters")
    @Email(message = "Should be email")
    private String email;

    @Size(min = 8, max = 50, message = "Password should between 8 and 50 characters")
    private String password;

    @Size(min = 8, max = 50, message = "Confirm Password should between 8 and 50 characters")
    private String confirmPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "RegisterDto [email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword + "]";
    }
}
