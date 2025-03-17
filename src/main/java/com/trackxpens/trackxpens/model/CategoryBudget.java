package com.trackxpens.trackxpens.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document("CategoryBudget")
public class CategoryBudget {
    @Id
    String category;
    int MaxAmountCents;

    public CategoryBudget(String category, int MaxAmountCents) {
        this.category = category;
        this.MaxAmountCents = MaxAmountCents;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMaxAmountCents() {
        return MaxAmountCents;
    }

    public void setMaxAmountCents(int MaxAmountCents) {
        this.MaxAmountCents = MaxAmountCents;
    }
}
