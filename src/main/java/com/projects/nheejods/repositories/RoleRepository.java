package com.projects.nheejods.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projects.nheejods.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
