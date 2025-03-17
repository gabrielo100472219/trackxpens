package com.trackxpens.trackxpens.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.time.LocalDate;
import java.util.List;

import com.trackxpens.trackxpens.model.Expense;

public interface IExpenseRepository extends MongoRepository<Expense, String>{
    @Query("{ '$expr': { '$and': [ { '$eq': [ { '$year': '$date' }, ?0 ] }, { '$eq': [ { '$month': '$date' }, ?1 ] } ] } }")
    List<Expense> findByMonthAndYear(int year, int month);
}
