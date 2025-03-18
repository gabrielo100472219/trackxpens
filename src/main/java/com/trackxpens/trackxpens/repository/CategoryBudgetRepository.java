package com.trackxpens.trackxpens.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.trackxpens.trackxpens.model.CategoryBudget;

@Repository
public interface CategoryBudgetRepository extends MongoRepository<CategoryBudget, String> {
    @Aggregation(pipeline = { "{ $project: { _id: 1 } }" })
    List<String> getAllCategories();
}
