package com.megastatics.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -829751052118118130L;
	
	private String fieldName;
	private String message;
	
	public FieldMessage() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}



	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
