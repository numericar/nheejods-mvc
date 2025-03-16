package com.projects.nheejods.dtos.boxs;

public class ShortBoxDto {
	private String month;
	private Integer year;
	private double incomeSummary;
	private double expenseSummary;
	private double remainingSummary;
	private int expensePercent;
	
	public ShortBoxDto() {
		
	}

	public ShortBoxDto(String month, Integer year, double incomeSummary, double expenseSummary, double remainingSummary, int expensePercent) {
		super();
		this.month = month;
		this.year = year;
		this.incomeSummary = incomeSummary;
		this.expenseSummary = expenseSummary;
		this.remainingSummary = remainingSummary;
		this.expensePercent = expensePercent;
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

	public int getExpensePercent() {
		return expensePercent;
	}

	public void setExpensePercent(int expensePercent) {
		this.expensePercent = expensePercent;
	}
}
