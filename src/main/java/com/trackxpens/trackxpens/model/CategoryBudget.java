package com.trackxpens.trackxpens.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document("CategoryBudget")
public class CategoryBudget {
    @Id
    String category;
    int amountCents;

    public CategoryBudget(String category, int amountCents) {
        this.category = category;
        this.amountCents = amountCents;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmountCents() {
        return amountCents;
    }

    public void setAmountCents(int amountCents) {
        this.amountCents = amountCents;
    }
}
