package com.wing;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralControllerAdvice {

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public void handleEmptyResultDataAccessException(final EmptyResultDataAccessException e,
			final HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value());
	}
}
