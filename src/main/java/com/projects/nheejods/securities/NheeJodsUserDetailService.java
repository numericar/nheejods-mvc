package com.projects.nheejods.securities;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.projects.nheejods.entities.User;
import com.projects.nheejods.repositories.UserRepository;

public class NheeJodsUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public NheeJodsUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Email is invalid");
        }

        return new NheeJodsUserDetail(userOptional.get());
    }

}
