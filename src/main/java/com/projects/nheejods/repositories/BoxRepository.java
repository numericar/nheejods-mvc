package com.projects.nheejods.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projects.nheejods.entities.Box;

@Repository
public interface BoxRepository extends CrudRepository<Box, Integer> {

}
