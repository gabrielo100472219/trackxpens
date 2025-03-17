package com.trackxpens.trackxpens.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.trackxpens.trackxpens.model.Expense;

@SpringBootTest
class ExpenseRepositoryTests {
    @Autowired
    private ExpenseRepository expenseRepo;
    private static List<String>idsInserted = new ArrayList<>();

    @AfterEach
    void deleteInsertedExpenses() {
        for (String id: idsInserted) {
            expenseRepo.deleteById(id);
        }
        idsInserted.clear();
    }
    
	@Test
	void correctFindingByYearMonthSingleExpense() {
        int month = 3;
        int year = 2025;
        String id = UUID.randomUUID().toString();
        Expense testExpense = new Expense(id, 40000, LocalDate.of(year, month, 17), "TestCategory");
        expenseRepo.save(testExpense);
        idsInserted.add(id);
        Expense foundExpense = expenseRepo.findByYearAndMonth(year, month).get(0);
        assertEquals(foundExpense.getId(), testExpense.getId());
        assertEquals(foundExpense.getAmountCents(), testExpense.getAmountCents());
        assertEquals(foundExpense.getDate(), testExpense.getDate());
        assertEquals(foundExpense.getCategory(), testExpense.getCategory());
	}

    @Test
    void correctFindingByYearMonthMultipleExpenses() {
        int month = 3;
        int year = 2025;

        // Create multiple expenses for the same month and year
        Expense expense1 = new Expense(UUID.randomUUID().toString(), 40000, LocalDate.of(year, month, 10), "Food");
        Expense expense2 = new Expense(UUID.randomUUID().toString(), 25000, LocalDate.of(year, month, 15), "Transport");
        Expense expense3 = new Expense(UUID.randomUUID().toString(), 60000, LocalDate.of(year, month, 22), "Rent");

        // Save them to the repository
        expenseRepo.saveAll(List.of(expense1, expense2, expense3));

        // Track inserted IDs for cleanup
        idsInserted.add(expense1.getId());
        idsInserted.add(expense2.getId());
        idsInserted.add(expense3.getId());

        // Fetch all expenses for the given month and year
        List<Expense> foundExpenses = expenseRepo.findByYearAndMonth(year, month);

        // Check that we found exactly 3 expenses
        assertEquals(3, foundExpenses.size());

        // Verify all inserted expenses are found
        List<String> foundIds = foundExpenses.stream().map(Expense::getId).toList();

        assertTrue(foundIds.contains(expense1.getId()));
        assertTrue(foundIds.contains(expense2.getId()));
        assertTrue(foundIds.contains(expense3.getId()));
    }
}