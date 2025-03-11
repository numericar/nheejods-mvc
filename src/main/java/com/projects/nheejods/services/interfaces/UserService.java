package com.projects.nheejods.services.interfaces;

import java.util.Optional;

import com.projects.nheejods.entities.Role;
import com.projects.nheejods.entities.User;

public interface UserService {
    boolean isDupplicateEmail(String email);
    void create(String email, String password, Role role);
    Optional<User> getUserByEmail(String email);
}
