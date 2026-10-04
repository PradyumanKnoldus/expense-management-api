package com.nashtechglobal.expensemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ExpenseNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleExpenseNotFound(ExpenseNotFoundException exception) {
		return createResponse(HttpStatus.NOT_FOUND, exception.getMessage(), Map.of());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidationErrors(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new LinkedHashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(error ->
			errors.putIfAbsent(error.getField(), error.getDefaultMessage()));

		return createResponse(HttpStatus.BAD_REQUEST, "Validation failed", errors);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleUnexpectedException(Exception exception) {
		return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", Map.of());
	}

	private ResponseEntity<ApiErrorResponse> createResponse(HttpStatus status, String message, Map<String, String> errors) {
		ApiErrorResponse response = new ApiErrorResponse(status.value(), message, errors);
		return ResponseEntity.status(status).body(response);
	}
}