package com.objectfrontier.training.jdbc.exceptions;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = 7921277938846318826L;
    private String errorMsg;

    public AppException(ErrorCode code) {
        this.errorMsg = code.getMsg();
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}

