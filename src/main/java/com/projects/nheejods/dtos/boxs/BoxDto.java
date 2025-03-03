package com.projects.nheejods.dtos.boxs;

import java.util.List;
import java.util.Optional;

public class BoxDto {

    private Optional<Integer> id;
    private List<BoxItemDto> incomes;
    private List<BoxItemDto> expenses;

    public BoxDto() {
    }

    public BoxDto(List<BoxItemDto> incomes, List<BoxItemDto> expenses) {
        this.incomes = incomes;
        this.expenses = expenses;
    }

    public Optional<Integer> getId() {
        return id;
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
