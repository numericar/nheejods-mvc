package com.projects.nheejods.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projects.nheejods.entities.BoxItem;

@Repository
public interface BoxItemRepository extends CrudRepository<BoxItem, Integer> {

}
