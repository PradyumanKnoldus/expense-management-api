package com.nashtechglobal.expensemanagement.expense.service;

import com.nashtechglobal.expensemanagement.expense.dto.ExpenseRequest;
import com.nashtechglobal.expensemanagement.expense.dto.ExpenseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseService {

	ExpenseResponse createExpense(ExpenseRequest request);

	List<ExpenseResponse> getAllExpenses();

	ExpenseResponse getExpenseById(Long id);

	ExpenseResponse updateExpense(Long id, ExpenseRequest request);

	void deleteExpense(Long id);

	List<ExpenseResponse> searchExpensesByCategory(String category);

	List<ExpenseResponse> findExpensesAboveAmount(BigDecimal minimumAmount);

	List<ExpenseResponse> findExpensesBetweenAmounts(BigDecimal minimumAmount, BigDecimal maximumAmount);

	Page<ExpenseResponse> getAllExpenses(Pageable pageable);
}