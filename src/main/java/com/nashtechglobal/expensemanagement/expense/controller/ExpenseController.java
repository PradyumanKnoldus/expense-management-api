package com.nashtechglobal.expensemanagement.expense.controller;

import com.nashtechglobal.expensemanagement.expense.dto.ExpenseRequest;
import com.nashtechglobal.expensemanagement.expense.dto.ExpenseResponse;
import com.nashtechglobal.expensemanagement.expense.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

	private final ExpenseService expenseService;

	public ExpenseController(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@PostMapping
	public ResponseEntity<ExpenseResponse> createExpense(@Valid @RequestBody ExpenseRequest request) {
		ExpenseResponse response = expenseService.createExpense(request);
		URI location = URI.create("/api/expenses/" + response.getId());
		return ResponseEntity.created(location).body(response);
	}

	@GetMapping
	public ResponseEntity<List<ExpenseResponse>> getAllExpenses() {
		return ResponseEntity.ok(expenseService.getAllExpenses());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ExpenseResponse> getExpenseById(@PathVariable Long id) {
		return ResponseEntity.ok(expenseService.getExpenseById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ExpenseResponse> updateExpense(
			@PathVariable Long id,
			@Valid @RequestBody ExpenseRequest request) {
		return ResponseEntity.ok(expenseService.updateExpense(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
		expenseService.deleteExpense(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<ExpenseResponse>> searchByCategory(@PathVariable String category) {
		return ResponseEntity.ok(expenseService.searchExpensesByCategory(category));
	}

	@GetMapping("/minimum-amount")
	public ResponseEntity<List<ExpenseResponse>> findExpensesAboveAmount(
			@RequestParam BigDecimal minimumAmount) {
		return ResponseEntity.ok(expenseService.findExpensesAboveAmount(minimumAmount));
	}

	@GetMapping("/amount-range")
	public ResponseEntity<List<ExpenseResponse>> findExpensesBetweenAmounts(
			@RequestParam BigDecimal minimumAmount,
			@RequestParam BigDecimal maximumAmount) {
		return ResponseEntity.ok(expenseService.findExpensesBetweenAmounts(minimumAmount, maximumAmount));
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ExpenseResponse>> getAllExpenses(Pageable pageable) {
		return ResponseEntity.ok(expenseService.getAllExpenses(pageable));
	}
}