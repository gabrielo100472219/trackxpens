package com.trackxpens.trackxpens.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trackxpens.trackxpens.model.CategoryBudget;


@SpringBootTest
public class CategoryBudgetRepositoryTests {
    @Autowired
    private CategoryBudgetRepository categoryBudgetRepo;
    private static List<String> idsInserted = new ArrayList<>();

    @AfterEach
    void deleteInsertedCategories() {
        for (String id: idsInserted) {
            categoryBudgetRepo.deleteById(id);
        }
        idsInserted.clear();
    }

    @Test
    void correctFindingOneCategoryBudget() {
        CategoryBudget categoryBudget = new CategoryBudget("Test category", 1000);
        categoryBudgetRepo.save(categoryBudget);
        idsInserted.add(categoryBudget.getCategory());
        CategoryBudget foundCategoryBudget = categoryBudgetRepo.findAll().get(0);
        assertEquals(categoryBudget.getCategory(), foundCategoryBudget.getCategory());
        assertEquals(categoryBudget.getMaxAmountCents(), foundCategoryBudget.getMaxAmountCents());
    }

    @Test
    void correctFindingMultipleCategoryBudgets() {
        CategoryBudget categoryBudget1 = new CategoryBudget("Test category 1", 1000);
        CategoryBudget categoryBudget2 = new CategoryBudget("Test category 2", 2000);
        CategoryBudget categoryBudget3 = new CategoryBudget("Test category 3", 3000);

        categoryBudgetRepo.save(categoryBudget1);
        categoryBudgetRepo.save(categoryBudget2);
        categoryBudgetRepo.save(categoryBudget3);

        idsInserted.add(categoryBudget1.getCategory());
        idsInserted.add(categoryBudget2.getCategory());
        idsInserted.add(categoryBudget3.getCategory());

        List<CategoryBudget> foundCategoryBudgets = categoryBudgetRepo.findAll();
        assertEquals(3, foundCategoryBudgets.size());

        List<String> foundIds = foundCategoryBudgets.stream().map(CategoryBudget::getCategory).toList();
        assertTrue(foundIds.contains(categoryBudget1.getCategory()));
        assertTrue(foundIds.contains(categoryBudget2.getCategory()));
        assertTrue(foundIds.contains(categoryBudget3.getCategory()));
    }    

    @Test
    void correctFindingOneCategory() {
        CategoryBudget categoryBudget = new CategoryBudget("Test category", 1000);
        categoryBudgetRepo.save(categoryBudget);
        idsInserted.add(categoryBudget.getCategory());
        String foundCategory = categoryBudgetRepo.getAllCategories().get(0);
        assertEquals(categoryBudget.getCategory(), foundCategory);
    }

    @Test
    void correctFindingMultipleCategories() {
        CategoryBudget categoryBudget1 = new CategoryBudget("Test category 1", 1000);
        CategoryBudget categoryBudget2 = new CategoryBudget("Test category 2", 2000);
        CategoryBudget categoryBudget3 = new CategoryBudget("Test category 3", 3000);

        categoryBudgetRepo.save(categoryBudget1);
        categoryBudgetRepo.save(categoryBudget2);
        categoryBudgetRepo.save(categoryBudget3);

        idsInserted.add(categoryBudget1.getCategory());
        idsInserted.add(categoryBudget2.getCategory());
        idsInserted.add(categoryBudget3.getCategory());

        List<String> foundCategories = categoryBudgetRepo.getAllCategories();
        assertEquals(3, foundCategories.size());
        assertTrue(foundCategories.contains(categoryBudget1.getCategory()));
        assertTrue(foundCategories.contains(categoryBudget2.getCategory()));
        assertTrue(foundCategories.contains(categoryBudget3.getCategory()));
    }    
}


