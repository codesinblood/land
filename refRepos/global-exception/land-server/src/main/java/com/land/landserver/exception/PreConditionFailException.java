/**
 * 
 */
package com.land.landserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author LB
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PreConditionFailException extends RuntimeException {
	
	public PreConditionFailException() {
		super();
	}

	public PreConditionFailException(String message) {
		super(message);
	}
}
