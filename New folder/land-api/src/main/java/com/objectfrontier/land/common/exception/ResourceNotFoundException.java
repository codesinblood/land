/**
 *
 */
package com.objectfrontier.land.common.exception;

import com.objectfrontier.land.common.exception.model.ErrorCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LB, gunasekaran.k
 * @since v1.0
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private List<ErrorCode> errorCode = new ArrayList<ErrorCode>();

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(ErrorCode error) {

        super();
        this.errorCode.add(error);
    }
}
