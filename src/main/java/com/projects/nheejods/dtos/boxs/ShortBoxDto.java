package com.projects.nheejods.dtos.boxs;

public class ShortBoxDto {
	private Integer id;
	private String month;
	private Integer year;
	private double incomeSummary;
	private double expenseSummary;
	private double remainingSummary;
	private double expensePercent;
	
	public ShortBoxDto() {
		
	}

	public ShortBoxDto(Integer id, String month, Integer year, double incomeSummary, double expenseSummary, double remainingSummary, double expensePercent) {
		this.id = id;
		this.month = month;
		this.year = year;
		this.incomeSummary = incomeSummary;
		this.expenseSummary = expenseSummary;
		this.remainingSummary = remainingSummary;
		this.expensePercent = expensePercent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public double getIncomeSummary() {
		return incomeSummary;
	}

	public void setIncomeSummary(double incomeSummary) {
		this.incomeSummary = incomeSummary;
	}

	public double getExpenseSummary() {
		return expenseSummary;
	}

	public void setExpenseSummary(double expenseSummary) {
		this.expenseSummary = expenseSummary;
	}

	public double getRemainingSummary() {
		return remainingSummary;
	}

	public void setRemainingSummary(double remainingSummary) {
		this.remainingSummary = remainingSummary;
	}

	public double getExpensePercent() {
		return expensePercent;
	}

	public void setExpensePercent(double expensePercent) {
		this.expensePercent = expensePercent;
	}
}
