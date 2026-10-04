package com.nashtechglobal.expensemanagement.expense.service;

import com.nashtechglobal.expensemanagement.exception.ExpenseNotFoundException;
import com.nashtechglobal.expensemanagement.expense.dto.ExpenseRequest;
import com.nashtechglobal.expensemanagement.expense.dto.ExpenseResponse;
import com.nashtechglobal.expensemanagement.expense.entity.Expense;
import com.nashtechglobal.expensemanagement.expense.repository.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExpenseServiceImpl implements ExpenseService {

	private final ExpenseRepository expenseRepository;

	public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	@Override
	@Transactional
	public ExpenseResponse createExpense(ExpenseRequest request) {
		Expense savedExpense = expenseRepository.save(toEntity(request));
		return toResponse(savedExpense);
	}

	@Override
	public List<ExpenseResponse> getAllExpenses() {
		return expenseRepository.findAll().stream()
				.map(this::toResponse)
				.toList();
	}

	@Override
	public ExpenseResponse getExpenseById(Long id) {
		return toResponse(findExpenseById(id));
	}

	@Override
	@Transactional
	public ExpenseResponse updateExpense(Long id, ExpenseRequest request) {
		Expense expense = findExpenseById(id);
		updateEntity(expense, request);
		return toResponse(expenseRepository.save(expense));
	}

	@Override
	@Transactional
	public void deleteExpense(Long id) {
		expenseRepository.delete(findExpenseById(id));
	}

	@Override
	public List<ExpenseResponse> searchExpensesByCategory(String category) {
		return expenseRepository.findByCategoryIgnoreCase(category).stream()
				.map(this::toResponse)
				.toList();
	}

	@Override
	public List<ExpenseResponse> findExpensesAboveAmount(BigDecimal minimumAmount) {
		return expenseRepository.findByAmountGreaterThan(minimumAmount).stream()
				.map(this::toResponse)
				.toList();
	}

	@Override
	public List<ExpenseResponse> findExpensesBetweenAmounts(BigDecimal minimumAmount, BigDecimal maximumAmount) {
		return expenseRepository.findByAmountBetween(minimumAmount, maximumAmount).stream()
				.map(this::toResponse)
				.toList();
	}

	@Override
	public Page<ExpenseResponse> getAllExpenses(Pageable pageable) {
		return expenseRepository.findAll(pageable).map(this::toResponse);
	}

	private Expense findExpenseById(Long id) {
		return expenseRepository.findById(id)
				.orElseThrow(() -> new ExpenseNotFoundException(id));
	}

	private Expense toEntity(ExpenseRequest request) {
		return new Expense(null, request.getTitle(), request.getDescription(), request.getCategory(),
				request.getAmount(), request.getExpenseDate());
	}

	private void updateEntity(Expense expense, ExpenseRequest request) {
		expense.setTitle(request.getTitle());
		expense.setDescription(request.getDescription());
		expense.setCategory(request.getCategory());
		expense.setAmount(request.getAmount());
		expense.setExpenseDate(request.getExpenseDate());
	}

	private ExpenseResponse toResponse(Expense expense) {
		return new ExpenseResponse(expense.getId(), expense.getTitle(), expense.getDescription(),
				expense.getCategory(), expense.getAmount(), expense.getExpenseDate());
	}
}