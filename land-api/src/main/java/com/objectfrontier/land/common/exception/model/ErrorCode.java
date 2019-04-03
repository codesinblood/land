package com.objectfrontier.land.common.exception.model;

import com.objectfrontier.land.common.exception.ErrorConstants;

/**
 * @author LB, Sriram Narayanan
 */
public enum ErrorCode {

    INTERNAL_ERROR(500, ErrorConstants.INTERNAL_SERVER_ERROR_MESSAGE),
    ACCESS_DENIED(401, ErrorConstants.ACCESS_DENIED_ERROR_MESSAGE),
    PRECONDITION_FAIL(412, ErrorConstants.PRECONDITION_FAILED_ERROR_MESSAGE),
    METHOD_ARGUMENT_NOT_VALID(400, ErrorConstants.METHOD_ARGUMENT_NOT_VALID_ERROR_MESSAGE),
    INVALID_REQUEST_BODY(400, ErrorConstants.INVALID_REQUEST_BODY_ERROR_MESSAGE),
    RESOURCE_EXITS(409, ErrorConstants.RESOURCE_EXITS_ERROR_MESSAGE),
    RESOURCE_NOT_FOUND(404, ErrorConstants.RESOURCE_NOT_FOUND_ERROR_MESSAGE),
    METHOD_TYPE_MISMATCH(400, ErrorConstants.METHOD_TYPE_MISMATCH_ERROR_MESSAGE),
    IMAGE_SIZE_LIMIT_EXCEEDED(400, ErrorConstants.IMAGE_SIZE_LIMIT_EXCEEDED),
    FILE_UPLOAD_ERROR(500, ErrorConstants.FILE_UPLOAD_ERROR),
    UNSUPPORTED_FILE_FORMAT(400, ErrorConstants.UNSUPPORTED_FILE_FORMAT),
    IMAGE_ENCODE_ERROR(500, ErrorConstants.IMAGE_ENCODE_ERROR),
    MAIL_SERVER_ERROR(500, ErrorConstants.MAIL_SERVER_ERROR);

    private long code;
    private String message;

    ErrorCode(long errorCode, String errorMessage) {
        this.code = errorCode;
        this.message = errorMessage;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long errorCode) {
        this.code = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String errorMessage) {
        this.message = errorMessage;
    }

    @Override
    public String toString() {
        return String.format("%o : %s", this.code, this.message);
    }
}
