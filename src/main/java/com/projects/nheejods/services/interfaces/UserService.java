package com.projects.nheejods.services.interfaces;

import com.projects.nheejods.entities.Role;

public interface UserService {
    boolean isDupplicateEmail(String email);
    void create(String email, String password, Role role);
}
