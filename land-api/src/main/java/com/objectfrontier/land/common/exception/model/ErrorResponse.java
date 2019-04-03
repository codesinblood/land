package com.objectfrontier.land.common.exception.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LB, Sriram Narayanan
 * <p>
 * This class has the predefined template of the standard error response.
 */
@Data
@NoArgsConstructor
public class ErrorResponse {

    private String errorType;
    private String errorMessage;
    private long errorCode;
    private Throwable errorCause;

}
