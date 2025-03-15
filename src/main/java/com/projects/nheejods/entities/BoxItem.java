package com.projects.nheejods.entities;

import java.time.LocalDateTime;

import com.projects.nheejods.enums.BoxItemType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "box_items")
public class BoxItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", length = 150, nullable = false)
    private String title;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "item_type", nullable = false)
    private BoxItemType itemType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "box_id")
    private Box box;

    public BoxItem() {
    }

    public BoxItem(String title, Double amount, BoxItemType itemType, Box box) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        this.title = title;
        this.amount = amount;
        this.itemType = itemType;
        this.box = box;
        this.createdAt = currentDateTime;
        this.updatedAt = currentDateTime;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BoxItemType getItemType() {
		return itemType;
	}

	public void setItemType(BoxItemType itemType) {
		this.itemType = itemType;
	}

	public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    @Override
    public String toString() {
        return "BoxItem [id=" + id + ", title=" + title + ", amount=" + amount + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt + ", box=" + box + "]";
    }

}
