package com.projects.nheejods.services.interfaces;

import com.projects.nheejods.entities.Box;

public interface BoxService {
	Box createBox(Integer year, Integer month, Integer createdBy);
}
