package com.projects.nheejods.services.impl;

import org.springframework.stereotype.Service;

import com.projects.nheejods.services.interfaces.MonthService;

@Service
public class MonthServiceImpl implements MonthService {

	private final String[] MONTHS = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

	@Override
	public String[] getNameMonths() {
		return this.MONTHS;
	}

	@Override
	public int getMonthIndex(String monthName) {
		int index = 1;
		
		for (int monthIndex = 0; monthIndex < this.MONTHS.length; monthIndex++) {
			String currentMonth = this.MONTHS[monthIndex];
			
			if (monthName.equals(currentMonth)) {
				index += monthIndex;
				break;
			}
		}
		
		return index;
	}

}
