package com.Exceptions;

public class OperationCancelException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public OperationCancelException(String s) {
		super(s);
	}
	
}
