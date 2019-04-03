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
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceExistsException extends RuntimeException {

  public ResourceExistsException() {
    super();
  }

  public ResourceExistsException(String message) {
    super(message);
  }
}

