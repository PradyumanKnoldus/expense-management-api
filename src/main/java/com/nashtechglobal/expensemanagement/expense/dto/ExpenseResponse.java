package com.nashtechglobal.expensemanagement.expense.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseResponse {

	private Long id;
	private String title;
	private String description;
	private String category;
	private BigDecimal amount;
	private LocalDate expenseDate;

	public ExpenseResponse() {
	}

	public ExpenseResponse(Long id, String title, String description, String category, BigDecimal amount, LocalDate expenseDate) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
		this.amount = amount;
		this.expenseDate = expenseDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDate getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(LocalDate expenseDate) {
		this.expenseDate = expenseDate;
	}
}