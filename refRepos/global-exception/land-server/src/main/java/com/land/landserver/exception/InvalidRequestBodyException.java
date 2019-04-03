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
public class InvalidRequestBodyException extends RuntimeException {
	
	public InvalidRequestBodyException() {
		super();
	}

	public InvalidRequestBodyException(String message) {
		super(message);
	}
}
