package com.trackxpens.trackxpens.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.trackxpens.trackxpens.model.Expense;
import com.trackxpens.trackxpens.repository.ExpenseRepository;

@SpringBootTest
public class ExpenseManagerTests {

    @Mock
    private ExpenseRepository expenseRepo;

    @InjectMocks
    private ExpenseManager expenseManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateExpense() {
        Expense expense = new Expense(40000, LocalDate.of(2025, 3, 18), "TestCategory");
        expenseManager.createExpense(expense);
        verify(expenseRepo, times(1)).insert(expense);
    }

    @Test
    public void testDeleteExpense() {
        Expense expense = new Expense(40000, LocalDate.of(2025, 3, 18), "TestCategory");
        expenseManager.deleteExpense(expense);
        verify(expenseRepo, times(1)).delete(expense);
    }

    @Test
    public void testUpdateExpense() {
        Expense oldExpense = new Expense(40000, LocalDate.of(2025, 3, 18), "TestCategoryOld");
        Expense newExpense = new Expense(10000, LocalDate.of(2025, 4, 13), "TestCategoryNew");
        expenseManager.updateExpense(oldExpense, newExpense);
        verify(expenseRepo, times(1)).delete(oldExpense);
        verify(expenseRepo, times(1)).insert(newExpense);
    }

    @Test
    public void testFindExpensesByYearMonth() {
        int year = 2025;
        int month = 3;
        List<Expense> expectedExpenses = Arrays.asList(new Expense(40000, LocalDate.of(2025, 3, 18), "TestCategoryOld"), new Expense(10000, LocalDate.of(2025, 4, 13), "TestCategoryNew"));
        when(expenseRepo.findByYearAndMonth(year, month)).thenReturn(expectedExpenses);

        List<Expense> actualExpenses = expenseManager.findExpensesByYearMonth(year, month);

        assertEquals(expectedExpenses, actualExpenses);
        verify(expenseRepo, times(1)).findByYearAndMonth(year, month);
    }
}