package com.akash.evm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.akash.evm.dto.ErrorResponse;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BaseException.class)
	public @ResponseBody ResponseEntity<Object> handleException(BaseException e) {
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getRootCuase(), e.getResolution(),
				e.getErrorCode());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
}