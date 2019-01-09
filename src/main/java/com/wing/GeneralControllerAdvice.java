package com.wing;

import java.io.IOException;
import java.util.Set;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralControllerAdvice {

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public void handleEmptyResultDataAccessException(final EmptyResultDataAccessException e,
			final HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value());
	}
	
	@ExceptionHandler(TransactionSystemException.class)
	public void handleTransactionSystemException(final TransactionSystemException e, final HttpServletResponse response) throws IOException {
		Throwable cause = e.getCause();
		
		if (cause instanceof RollbackException) {
			cause = cause.getCause();
			
			if (cause instanceof ConstraintViolationException) {
				Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause).getConstraintViolations();
				
				response.sendError(HttpStatus.BAD_REQUEST.value(), violations.toString());
				
				return;
			}
		}
		
		response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}
	
}
