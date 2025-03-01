package com.projects.nheejods.services.interfaces;

import com.projects.nheejods.entities.Role;

public interface RoleService {
    Role getAdminRole() throws Exception;
    Role getMemberRole() throws Exception;
}
