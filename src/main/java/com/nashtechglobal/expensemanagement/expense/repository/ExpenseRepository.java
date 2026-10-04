package com.nashtechglobal.expensemanagement.expense.repository;

import com.nashtechglobal.expensemanagement.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	List<Expense> findByCategoryIgnoreCase(String category);

	List<Expense> findByAmountGreaterThan(BigDecimal amount);

	@Query("SELECT e FROM Expense e WHERE e.amount BETWEEN :minAmount AND :maxAmount")
	List<Expense> findByAmountBetween(@Param("minAmount") BigDecimal minAmount,
									 @Param("maxAmount") BigDecimal maxAmount);
}