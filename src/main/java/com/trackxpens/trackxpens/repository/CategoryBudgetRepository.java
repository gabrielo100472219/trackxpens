package com.trackxpens.trackxpens.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.trackxpens.trackxpens.model.CategoryBudget;

public interface CategoryBudgetRepository extends MongoRepository<CategoryBudget, String> {
}
