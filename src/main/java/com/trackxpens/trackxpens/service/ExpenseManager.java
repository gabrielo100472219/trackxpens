package com.trackxpens.trackxpens.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trackxpens.trackxpens.model.Expense;
import com.trackxpens.trackxpens.repository.ExpenseRepository;

@Service
public class ExpenseManager {
    private ExpenseRepository expenseRepo;

    public List<Expense> findExpensesByYearMonth(int year, int month) {
        List<Expense> expenses = expenseRepo.findByYearAndMonth(year, month);
        return expenses;
    }

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
