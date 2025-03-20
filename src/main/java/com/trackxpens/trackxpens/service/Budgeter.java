package com.trackxpens.trackxpens.service;

import java.util.Optional;

import com.trackxpens.trackxpens.model.CategoryBudget;
import com.trackxpens.trackxpens.repository.CategoryBudgetRepository;

public class Budgeter {
    private CategoryBudgetRepository categoryBudgetRepo;

    public void createCategory(String name, int maxAmountCents) {
        CategoryBudget categoryBudget = new CategoryBudget(name, maxAmountCents);
        categoryBudgetRepo.insert(categoryBudget);
    }

    public void deleteCategory(String name) {
        Optional<CategoryBudget> optionalCategoryBudget = categoryBudgetRepo.findById(name);
        if (optionalCategoryBudget.isPresent()) {
            categoryBudgetRepo.deleteById(name);
        } else {
            throw new IllegalArgumentException("Category with name " + name + " does not exist." );
        }
    }

    public void updateCategory(String oldName, String newName) {
        Optional<CategoryBudget> optionalCategoryBudget = categoryBudgetRepo.findById(oldName);
        if (optionalCategoryBudget.isPresent()) {
            int maxAmountCents = optionalCategoryBudget.get().getMaxAmountCents();
            categoryBudgetRepo.deleteById(oldName);
            CategoryBudget categoryBudget = new CategoryBudget(newName, maxAmountCents);
            categoryBudgetRepo.insert(categoryBudget);
        } else {
            throw new IllegalArgumentException("Category with name " + oldName + " does not exist.");
        }
    }

    public void updateBudget(String name, int newAmount) {
        Optional<CategoryBudget> optionalCategoryBudget = categoryBudgetRepo.findById(name);
        if (optionalCategoryBudget.isPresent()) {
            CategoryBudget categoryBudget = optionalCategoryBudget.get();
            categoryBudget.setMaxAmountCents(newAmount);
            categoryBudgetRepo.save(categoryBudget);
        } else {
            throw new IllegalArgumentException("Category with name " + name + " does not exist." );
        }
    }


}
