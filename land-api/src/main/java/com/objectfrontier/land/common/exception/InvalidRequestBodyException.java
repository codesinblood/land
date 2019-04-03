/**
 *
 */
package com.objectfrontier.land.common.exception;

import com.objectfrontier.land.common.exception.model.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author LB
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestBodyException extends RuntimeException {

    private static final long serialVersionUID = 587734320992857977L;
    ErrorCode error;

    public InvalidRequestBodyException(ErrorCode error) {
        this.error = error;
    }

    public InvalidRequestBodyException(ErrorCode error, Throwable cause) {
        super(cause);
        this.error = error;
    }
}
