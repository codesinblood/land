package com.objectfrontier.land.common.exception;

import com.objectfrontier.land.common.exception.model.ErrorCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author isaac.murugaian
 */
public class EmailException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private List<ErrorCode> errorCode = new ArrayList<ErrorCode>();

    public EmailException() {
        super();
    }

    public EmailException(ErrorCode error) {

        super();
        this.errorCode.add(error);
    }

}
