package com.objectfrontier.training.web.exceptions;

import java.util.ArrayList;
import java.util.List;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    List<ErrorCode> errorCodes;

//    public AppException(errorCode) {
//        this.errorMessage = errorCode;
//    }
;
    public AppException(ErrorCode error) {
        super();
        this.errorCodes = new ArrayList<>();
        errorCodes.add(error);
    }

    public AppException(List<ErrorCode> errorMessages) {
        super();
        this.errorCodes = errorMessages;
    }

    public AppException(ErrorCode errorMessage, Throwable cause) {
        super(cause);
        this.errorCodes = new ArrayList<>();
        errorCodes.add(errorMessage);
    }


    public List<ErrorCode> getErrorCodes() {
        return errorCodes;
    }
}
