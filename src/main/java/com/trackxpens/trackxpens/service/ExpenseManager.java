package com.trackxpens.trackxpens.service;

import org.springframework.stereotype.Service;

import com.trackxpens.trackxpens.model.Expense;
import com.trackxpens.trackxpens.repository.ExpenseRepository;

@Service
public class ExpenseManager {
    private ExpenseRepository expenseRepo;

    public void createExpense(Expense expense) {
        expenseRepo.insert(expense);
    }

    public void deleteExpense(Expense expense) {
        expenseRepo.delete(expense);
    }

    public void updateExpense(Expense oldExpense, Expense newExpense) {
        expenseRepo.delete(oldExpense);
        expenseRepo.insert(newExpense);
    }
}
