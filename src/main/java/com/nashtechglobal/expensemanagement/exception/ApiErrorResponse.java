package com.nashtechglobal.expensemanagement.exception;

import java.util.Map;

public record ApiErrorResponse(int status, String message, Map<String, String> errors) {
}