package com.projects.nheejods.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projects.nheejods.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
