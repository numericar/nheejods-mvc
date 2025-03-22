package com.projects.nheejods.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.nheejods.dtos.boxs.BoxItemDto;
import com.projects.nheejods.entities.Box;
import com.projects.nheejods.entities.BoxItem;
import com.projects.nheejods.entities.User;
import com.projects.nheejods.enums.BoxItemType;
import com.projects.nheejods.repositories.BoxItemRepository;
import com.projects.nheejods.repositories.BoxRepository;
import com.projects.nheejods.services.interfaces.BoxService;

import jakarta.transaction.Transactional;

@Service
public class BoxServiceImpl implements BoxService {
	
	private final BoxRepository boxRepository;
	private final BoxItemRepository boxItemRepository;
	
	public BoxServiceImpl(BoxRepository boxRepository, BoxItemRepository boxItemRepository) {
		this.boxRepository = boxRepository;
		this.boxItemRepository = boxItemRepository;
	}

	@Override
	public Box createBox(Integer year, Integer month, User createdBy) {
		Box box = new Box(year, month, createdBy);
		
		Box boxCreated = this.boxRepository.save(box);
		
		return boxCreated;
	}

	@Override
	public boolean isExist(int monthIndex, int year, Integer userId) {
		return false;
	}

	@Override
	public void appendIncomeItem(Box box, String title, double amount) {
		BoxItemType itemType = BoxItemType.INCOME;
		
		BoxItem boxItem = new BoxItem(title, amount, itemType, box);
		
		this.boxItemRepository.save(boxItem);
	}

	@Override
	public void appendExpenseItem(Box box, String title, double amount) {
		BoxItemType itemType = BoxItemType.EXPENSE;
		
		BoxItem boxItem = new BoxItem(title, amount, itemType, box);
		
		this.boxItemRepository.save(boxItem);
	}

	@Override
	@Transactional
	public void appendRangIncomeItem(List<BoxItemDto> items, Box box) {
		BoxItemType itemType = BoxItemType.INCOME;
		
		List<BoxItem> boxItems = new ArrayList<>();
		
		for (BoxItemDto income : items) {
			BoxItem boxItem = new BoxItem(income.getTitle(), income.getAmount(), itemType, box);
			boxItems.add(boxItem);
		}
		
		this.boxItemRepository.saveAll(boxItems);
	}

	@Override
	@Transactional
	public void appendRangExpenseItem(List<BoxItemDto> items, Box box) {
		BoxItemType itemType = BoxItemType.EXPENSE;
		
		List<BoxItem> boxItems = new ArrayList<>();
		
		for (BoxItemDto income : items) {
			BoxItem boxItem = new BoxItem(income.getTitle(), income.getAmount(), itemType, box);
			boxItems.add(boxItem);
		}
		
		this.boxItemRepository.saveAll(boxItems);
		
	}

	@Override
	public Optional<List<Box>> getBoxByUser(User user) {
		return this.boxRepository.findByUserId(user.getId());
	}

	@Override
	public Optional<Box> getBoxById(Integer id) {
		return this.boxRepository.findById(id);
	}
}
