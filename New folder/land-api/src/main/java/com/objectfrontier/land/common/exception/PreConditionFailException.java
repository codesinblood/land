/**
 *
 */
package com.objectfrontier.land.common.exception;

import com.objectfrontier.land.common.exception.model.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LB
 *@author jayanth.subramanian
 *@since v1.0
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PreConditionFailException extends RuntimeException {

    private static final long serialVersionUID = -786904646740613713L;
    private List<ErrorCode> errorCode = new ArrayList<ErrorCode>();

    public PreConditionFailException() {
        super();
    }

    public PreConditionFailException(ErrorCode error) {

        super();
        this.errorCode.add(error);
    }
}
