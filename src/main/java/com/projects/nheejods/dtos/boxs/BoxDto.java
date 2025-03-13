package com.projects.nheejods.dtos.boxs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.validation.constraints.NotNull;

public class BoxDto {

	private Optional<Integer> id;
	
	@NotNull(message = "month should not be null")
	private Optional<String> month;
	
	@NotNull(message = "year should not be null")
	private Optional<Integer> year;
	
	private List<BoxItemDto> incomes = new ArrayList<>();
	
	private List<BoxItemDto> expenses = new ArrayList<>();;

	public BoxDto() {
	}

	public BoxDto(List<BoxItemDto> incomes, List<BoxItemDto> expenses) {
		this.incomes = incomes;
		this.expenses = expenses;
	}

	public Optional<Integer> getId() {
		return id;
	}

	public Optional<String> getMonth() {
		return month;
	}

	public void setMonth(Optional<String> month) {
		this.month = month;
	}

	public Optional<Integer> getYear() {
		return year;
	}

	public void setYear(Optional<Integer> year) {
		this.year = year;
	}

	public void setId(Optional<Integer> id) {
		this.id = id;
	}

	public List<BoxItemDto> getIncomes() {
		return incomes;
	}

	public void setIncomes(List<BoxItemDto> incomes) {
		this.incomes = incomes;
	}

	public List<BoxItemDto> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<BoxItemDto> expenses) {
		this.expenses = expenses;
	}

	@Override
	public String toString() {
		return "BoxDto [id=" + id + ", incomes=" + incomes + ", expenses=" + expenses + "]";
	}
}
  