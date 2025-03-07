package com.projects.nheejods.securities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.projects.nheejods.entities.Role;
import com.projects.nheejods.entities.User;

public class NheeJodsUserDetail implements UserDetails {

    private final User user;

    public NheeJodsUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role userRole = this.user.getRole();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.getName());
        List<SimpleGrantedAuthority> authorities = List.of(authority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
