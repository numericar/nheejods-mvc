package com.projects.nheejods.dtos.boxs;

public class BoxItemDto {
    private Integer id;
    private String title;
    private double amount;

    public BoxItemDto() {
    }

    public BoxItemDto(String title, double amount) {
        this.title = title;
        this.amount = amount;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BoxItemDto [id=" + id + ", title=" + title + ", amount=" + amount + "]";
    }
}
