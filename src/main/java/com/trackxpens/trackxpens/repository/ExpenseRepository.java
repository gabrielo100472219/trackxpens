package com.trackxpens.trackxpens.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.trackxpens.trackxpens.model.Expense;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String>{
    @Query("{ '$expr': { '$and': [ { '$eq': [ { '$year': '$date' }, ?0 ] }, { '$eq': [ { '$month': '$date' }, ?1 ] } ] } }")
    List<Expense> findByYearAndMonth(int year, int month);
}
