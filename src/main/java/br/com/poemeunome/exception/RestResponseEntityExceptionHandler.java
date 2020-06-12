package br.com.poemeunome.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ErroException> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		ErroException err = new ErroException(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(SQLOperationsException.class)
	public ResponseEntity<ErroException> sqlException(SQLOperationsException e, HttpServletRequest request) {

		ErroException err = new ErroException(System.currentTimeMillis(), HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.getReasonPhrase(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErroException> validationException(ValidationException e, HttpServletRequest request) {

		ErroException err = new ErroException(System.currentTimeMillis(), HttpStatus.NOT_ACCEPTABLE.value(),
				HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<ErroException> validationException(NegocioException e, HttpServletRequest request) {

		ErroException err = new ErroException(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED.getReasonPhrase(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}

}
