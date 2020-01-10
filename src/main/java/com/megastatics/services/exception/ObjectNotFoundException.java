package com.megastatics.services.exception;

public class ObjectNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6942474264958767868L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
	

}
