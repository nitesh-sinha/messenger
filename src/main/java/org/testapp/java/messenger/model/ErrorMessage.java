package org.testapp.java.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	
	private String errorMsg;
	private int errorCode;
	private String errorDoc;
	
	public ErrorMessage() {
		
	}
	
	public ErrorMessage(String errorMsg, int errorCode, String errorDoc) {
		super();
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
		this.errorDoc = errorDoc;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDoc() {
		return errorDoc;
	}
	public void setErrorDoc(String errorDoc) {
		this.errorDoc = errorDoc;
	}
	
	

}
