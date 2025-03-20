package com.trackxpens.trackxpens.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.trackxpens.trackxpens.model.CategoryBudget;
import com.trackxpens.trackxpens.repository.CategoryBudgetRepository;

public class BudgeterTests {

    @Mock
    private CategoryBudgetRepository categoryBudgetRepo;

    @InjectMocks
    private Budgeter budgeter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCategory() {
        String name = "TestCategory";
        int maxAmountCents = 10000;

        budgeter.createCategory(name, maxAmountCents);

        ArgumentCaptor<CategoryBudget> captor = ArgumentCaptor.forClass(CategoryBudget.class);
        verify(categoryBudgetRepo, times(1)).insert(captor.capture());
        CategoryBudget capturedCategoryBudget = captor.getValue();

        assertEquals(name, capturedCategoryBudget.getCategory());
        assertEquals(maxAmountCents, capturedCategoryBudget.getMaxAmountCents());
    }

    @Test
    public void testDeleteCategory() {
        String name = "TestCategory";
        CategoryBudget categoryBudget = new CategoryBudget(name, 10000);
        when(categoryBudgetRepo.findById(name)).thenReturn(Optional.of(categoryBudget));

        budgeter.deleteCategory(name);

        verify(categoryBudgetRepo, times(1)).deleteById(name);
    }

    @Test
    public void testDeleteCategoryNotFound() {
        String name = "NonExistentCategory";
        when(categoryBudgetRepo.findById(name)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            budgeter.deleteCategory(name);
        });
    }

    @Test
    public void testUpdateCategory() {
        String oldName = "OldCategory";
        String newName = "NewCategory";
        int maxAmountCents = 10000;
        CategoryBudget oldCategoryBudget = new CategoryBudget(oldName, maxAmountCents);
        when(categoryBudgetRepo.findById(oldName)).thenReturn(Optional.of(oldCategoryBudget));

        budgeter.updateCategory(oldName, newName);

        verify(categoryBudgetRepo, times(1)).deleteById(oldName);
        ArgumentCaptor<CategoryBudget> captor = ArgumentCaptor.forClass(CategoryBudget.class);
        verify(categoryBudgetRepo, times(1)).insert(captor.capture());
        CategoryBudget capturedCategoryBudget = captor.getValue();

        assertEquals(newName, capturedCategoryBudget.getCategory());
        assertEquals(maxAmountCents, capturedCategoryBudget.getMaxAmountCents());
    }

    @Test
    public void testUpdateCategoryNotFound() {
        String oldName = "NonExistentCategory";
        String newName = "NewCategory";
        when(categoryBudgetRepo.findById(oldName)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            budgeter.updateCategory(oldName, newName);
        });
    }

    @Test
    public void testUpdateBudget() {
        String name = "TestCategory";
        int newAmount = 20000;
        CategoryBudget categoryBudget = new CategoryBudget(name, 10000);
        when(categoryBudgetRepo.findById(name)).thenReturn(Optional.of(categoryBudget));

        budgeter.updateBudget(name, newAmount);

        verify(categoryBudgetRepo, times(1)).save(categoryBudget);
        assertEquals(newAmount, categoryBudget.getMaxAmountCents());
    }

    @Test
    public void testUpdateBudgetNotFound() {
        String name = "NonExistentCategory";
        int newAmount = 20000;
        when(categoryBudgetRepo.findById(name)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            budgeter.updateBudget(name, newAmount);
        });
    }
}