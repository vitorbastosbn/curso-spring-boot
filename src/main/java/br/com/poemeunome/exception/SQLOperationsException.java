package br.com.poemeunome.exception;

import java.io.Serializable;

public class SQLOperationsException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public SQLOperationsException(String message) {
		super(message);
	}

}
