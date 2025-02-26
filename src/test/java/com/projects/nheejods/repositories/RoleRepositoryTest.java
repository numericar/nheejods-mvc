package com.projects.nheejods.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.projects.nheejods.entities.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepo;

    @Test
    public void testCreateMemberRole() {
        Role memberRole = new Role("member");

        Role memberRoleCreated = this.roleRepo.save(memberRole);

        assertNotNull(memberRoleCreated);
    }

    @Test 
    public void testCreateAdminRole() {
        Role adminRole = new Role("admin");

        Role adminRoleCreated = this.roleRepo.save(adminRole);

        assertNotNull(adminRoleCreated);
    }

}
