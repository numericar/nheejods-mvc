package com.projects.nheejods.services.interfaces;

public interface UserService {
    boolean isDupplicateEmail(String email);
    void create(String email, String password);
}
