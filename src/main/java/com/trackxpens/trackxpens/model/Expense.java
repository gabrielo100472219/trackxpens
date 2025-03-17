package com.trackxpens.trackxpens.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;


@Document("Expense")
public class Expense {
    @Id
    private String id;
    private int amountCents;
    private LocalDate date;
    private String category;

    public Expense(String id, int amountCents, LocalDate date, String category) {
        this.id = id;
        this.amountCents = amountCents;
        this.date = date;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmountCents() {
        return amountCents;
    }

    public void setAmountCents(int amountCents) {
        this.amountCents = amountCents;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    
}
