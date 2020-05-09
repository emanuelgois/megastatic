package com.megastatics.services.exception;

public class DataIntegrityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6942474264958767868L;
	
	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg,cause);
	}
	

}
