package com.projects.nheejods.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.nheejods.entities.Role;
import com.projects.nheejods.entities.User;
import com.projects.nheejods.repositories.UserRepository;
import com.projects.nheejods.services.interfaces.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isDupplicateEmail(String email) {
        Optional<User> userOptional = this.userRepository.findByEmail(email);

        return userOptional.isPresent();
    }

    @Transactional
    @Override
    public void create(String email, String password, Role role) {
        User user = new User(email, password, role);

        // set role to user

        this.userRepository.save(user);
    }

	@Override
	public Optional<User> getUserByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

}
