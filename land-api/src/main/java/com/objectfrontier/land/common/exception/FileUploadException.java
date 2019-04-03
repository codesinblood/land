package com.objectfrontier.land.common.exception;

import com.objectfrontier.land.common.exception.model.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Sriram Narayanan
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileUploadException extends RuntimeException {

    private static final long serialVersionUID = -1847111083051547456L;
    ErrorCode error;

    public FileUploadException(ErrorCode errorCode) {
        this.error = errorCode;
    }

    public FileUploadException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.error = errorCode;
    }

}
