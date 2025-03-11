package com.projects.nheejods.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.projects.nheejods.services.impl.MonthServiceImpl;
import com.projects.nheejods.services.interfaces.MonthService;

public class MonthServiceTest {

	private final MonthService monthService;
	
	@Autowired
	public MonthServiceTest() {
		this.monthService = new MonthServiceImpl();
	}
	
	@Test
	public void testGetMonths() {
		String[] months = this.monthService.getNameMonths();
		
		for (String month : months) {
			System.out.print(month + " ");
		}
		
		assertTrue(months.length == 12);
	}
	
	@Test
	public void testGetIndexMonth() {
		String month = "June";
		int monthIndex = this.monthService.getMonthIndex(month);
		
		int expect = 6;
		System.out.println("Current month index : " + monthIndex);
		
		assertTrue(monthIndex == expect);
	}
	
}
