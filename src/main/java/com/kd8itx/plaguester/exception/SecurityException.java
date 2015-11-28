package com.kd8itx.plaguester.exception;

public class SecurityException extends Exception {

	private static final long serialVersionUID = 5910556354888636836L;

	public SecurityException() {}
	
	public SecurityException(String message) {
		super(message);
	}
}
