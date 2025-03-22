package com.projects.nheejods.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.projects.nheejods.dtos.boxs.BoxItemDto;
import com.projects.nheejods.entities.Box;
import com.projects.nheejods.entities.User;

public interface BoxService {
	Box createBox(Integer year, Integer month, User createdBy);
	void appendIncomeItem(Box box, String title, double amount);
	void appendExpenseItem(Box box, String title, double amount);
	void appendRangIncomeItem(List<BoxItemDto> items, Box box);
	void appendRangExpenseItem(List<BoxItemDto> items, Box box);
	boolean isExist(int monthIndex, int year, Integer userId);
	Optional<List<Box>> getBoxByUser(User user);
	Optional<Box> getBoxById(Integer id);
}
