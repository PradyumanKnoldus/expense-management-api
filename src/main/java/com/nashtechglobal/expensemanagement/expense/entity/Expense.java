package com.nashtechglobal.expensemanagement.expense.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;
	private String category;
	private BigDecimal amount;
	private LocalDate expenseDate;

	public Expense() {
	}

	public Expense(Long id, String title, String description, String category, BigDecimal amount, LocalDate expenseDate) {
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

	@Override
	public String toString() {
		return "Expense{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", category='" + category + '\'' +
				", amount=" + amount +
				", expenseDate=" + expenseDate +
				'}';
	}
}