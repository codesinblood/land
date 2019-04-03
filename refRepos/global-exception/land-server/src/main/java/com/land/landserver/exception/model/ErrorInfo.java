package com.land.landserver.exception.model;

import java.util.Date;

/**
 * @author LB
 *
 */
public class ErrorInfo {

	private Date timeStamp;
	private String errorType;
	private String errorMessage;
	private long errorCode;
	private Throwable errorCause;
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public long getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}
	public Throwable getErrorCause() {
		return errorCause;
	}
	public void setErrorCause(Throwable errorCause) {
		this.errorCause = errorCause;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	
}
