package com.nashtechglobal.expensemanagement.exception;

public class ExpenseNotFoundException extends RuntimeException {

	public ExpenseNotFoundException(Long expenseId) {
		super("Expense not found with ID: " + expenseId);
	}
}