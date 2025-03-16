package com.projects.nheejods.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projects.nheejods.entities.Box;

@Repository
public interface BoxRepository extends CrudRepository<Box, Integer> {
	
	@Query("SELECT b FROM Box b WHERE b.user.id = :userId")
	Optional<List<Box>> findByUserId(@Param("userId") Integer userId);
}
