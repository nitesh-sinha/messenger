package org.testapp.java.messenger.exception;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1387969922824361245L;
	
	public DataNotFoundException(String message) {
		super(message);
	}

}
