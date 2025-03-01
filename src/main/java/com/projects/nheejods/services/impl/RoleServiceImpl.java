package com.projects.nheejods.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.nheejods.entities.Role;
import com.projects.nheejods.repositories.RoleRepository;
import com.projects.nheejods.services.interfaces.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getAdminRole() throws Exception {
        Optional<Role> adminRoleOptional = this.roleRepository.findById(1);

        if (adminRoleOptional.isEmpty()) {
            throw new Exception("Admin role not found");
        }

        return adminRoleOptional.get();
    }

    @Override
    public Role getMemberRole() throws Exception {
        Optional<Role> memberRoleOptional = this.roleRepository.findById(2);

        if (memberRoleOptional.isEmpty()) {
            throw new Exception("Member role not found");
        }

        return memberRoleOptional.get();
    }

}
