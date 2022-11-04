package com.cognixia.jump.exception;

public class DuplicateProductException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public DuplicateProductException(String msg) {
		super(msg);
	}
}
