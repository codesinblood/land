package com.objectfrontier.land.common.exception;

import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.common.exception.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LB, Sriram Narayanan
 * Global Exception Handler that handles all exceptions. All the classes are scanned for exception and is an Exception occurs, the ResponseEntity is constructed.
 */
@ControllerAdvice(basePackages = "com.objectfrontier.land")
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*
     * Whenever a "ResourseNotFound(404)" exception is thrown from service, the error response is intercepted by the handler and a new ResponseEntity is manipulated.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<List<ErrorResponse>> handleRecordNotFoundException(ResourceNotFoundException ex) {

        ErrorResponse error = new ErrorResponse();
        error.setErrorType(ErrorConstants.NOT_FOUND);
        error.setErrorMessage(
                StringUtils.isEmpty(ex.getMessage()) ? ErrorCode.RESOURCE_NOT_FOUND.getMessage() : ex.getMessage());
        error.setErrorCode(ErrorCode.RESOURCE_NOT_FOUND.getCode());
        LOGGER.debug(ex.getStackTrace().toString());
        return new ResponseEntity<List<ErrorResponse>>(Arrays.asList(error), HttpStatus.NOT_FOUND);
    }

    /*
     * Scenarios in which generic exceptions are thrown from Service layer are handled in this method. Corresponding ResponseEntity is created.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<List<ErrorResponse>> handleGenericException(Exception ex) {

        ErrorResponse error = new ErrorResponse();
        error.setErrorType(ErrorConstants.INTERNAL_ERROR);
        error.setErrorMessage(
                StringUtils.isEmpty(ex.getMessage()) ? ErrorCode.INTERNAL_ERROR.getMessage() : ex.getMessage());
        error.setErrorCode(ErrorCode.INTERNAL_ERROR.getCode());
        LOGGER.debug(ex.getMessage());
        return new ResponseEntity<List<ErrorResponse>>(Arrays.asList(error), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * Whenever a "AccessDenied(401)" exception is thrown from service, the error response is intercepted by the handler and a new ResponseEntity is manipulated.
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<List<ErrorResponse>> handleAccessDeniedException(AccessDeniedException ex) {

        ErrorResponse error = new ErrorResponse();
        error.setErrorType(ErrorConstants.UNAUTHORIZED);
        error.setErrorMessage(
                StringUtils.isEmpty(ex.getMessage()) ? ErrorCode.ACCESS_DENIED.getMessage() : ex.getMessage());
        error.setErrorCode(ErrorCode.ACCESS_DENIED.getCode());
        LOGGER.debug(ex.getStackTrace().toString());
        return new ResponseEntity<List<ErrorResponse>>(Arrays.asList(error), HttpStatus.UNAUTHORIZED);
    }

    /*
     * Whenever a "Precondition failed(412)" exception is thrown from service, the error response is intercepted by the handler and a new ResponseEntity is manipulated.
     */
    @ExceptionHandler(PreConditionFailException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ResponseEntity<List<ErrorResponse>> handlePreConditionFailException(PreConditionFailException ex) {

        ErrorResponse error = new ErrorResponse();
        error.setErrorType(ErrorConstants.PRECONDITION_FAIL);
        error.setErrorMessage(
                StringUtils.isEmpty(ex.getMessage()) ? ErrorCode.PRECONDITION_FAIL.getMessage() : ex.getMessage());
        error.setErrorCode(ErrorCode.PRECONDITION_FAIL.getCode());
        LOGGER.debug(ex.getStackTrace().toString());
        return new ResponseEntity<List<ErrorResponse>>(Arrays.asList(error), HttpStatus.PRECONDITION_FAILED);
    }

    /*
     * Whenever a "BadRequest(400)" exception is thrown from service, the error response is intercepted by the handler and a new ResponseEntity is manipulated.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ErrorResponse>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<ErrorResponse> errors = new ArrayList<ErrorResponse>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            ErrorResponse currentError = new ErrorResponse();
            currentError.setErrorType(ErrorConstants.REQUEST_ERROR);
            currentError.setErrorMessage(error.getDefaultMessage());
            currentError.setErrorCode(ErrorCode.METHOD_ARGUMENT_NOT_VALID.getCode());
            errors.add(currentError);
        }
        LOGGER.debug(ex.getStackTrace().toString());
        return new ResponseEntity<List<ErrorResponse>>(errors, HttpStatus.BAD_REQUEST);
    }

    /*
     * Whenever a "BadRequest(400)" exception is thrown from service, the error response is intercepted by the handler and a new ResponseEntity is manipulated.
     */
    @ExceptionHandler(InvalidRequestBodyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ErrorResponse>> handleInvalidRequestBodyExceptions(InvalidRequestBodyException ex) {

        ErrorResponse error = new ErrorResponse();
        error.setErrorType(ErrorConstants.REQUEST_ERROR);
        error.setErrorMessage(
                StringUtils.isEmpty(ex.getMessage()) ? ErrorCode.INVALID_REQUEST_BODY.getMessage() : ex.getMessage());
        error.setErrorCode(ErrorCode.INVALID_REQUEST_BODY.getCode());
        LOGGER.debug(ex.getStackTrace().toString());
        return new ResponseEntity<List<ErrorResponse>>(Arrays.asList(error), HttpStatus.BAD_REQUEST);
    }

    /*
     * Whenever a "BadRequest(400)" exception is thrown from service, the error response is intercepted by the handler and a new ResponseEntity is manipulated.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ErrorResponse>> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {

        ErrorResponse error = new ErrorResponse();
        error.setErrorType(ErrorConstants.METHOD_TYPE_MISMATCH);
        error.setErrorMessage(
                StringUtils.isEmpty(ex.getMessage()) ? ErrorCode.METHOD_TYPE_MISMATCH.getMessage() : ex.getMessage());
        error.setErrorCode(ErrorCode.METHOD_TYPE_MISMATCH.getCode());
        LOGGER.debug(ex.getStackTrace().toString());
        return new ResponseEntity<List<ErrorResponse>>(Arrays.asList(error), HttpStatus.BAD_REQUEST);
    }

    /*
     * Whenever a "File Upload Error(500)" exception is thrown from service, the error response is intercepted by the handler and a new ResponseEntity is manipulated.
     */
    @ExceptionHandler(FileUploadException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<List<ErrorResponse>> handleFileUploadException(FileUploadException ex) {

        ErrorResponse error = new ErrorResponse();
        error.setErrorType(ErrorConstants.INTERNAL_ERROR);
        error.setErrorMessage(
                StringUtils.isEmpty(ex.getMessage()) ? ErrorCode.INTERNAL_ERROR.getMessage() : ex.getMessage());
        error.setErrorCode(ErrorCode.INTERNAL_ERROR.getCode());
        LOGGER.debug(ex.getStackTrace().toString());
        ex.printStackTrace();
        return new ResponseEntity<List<ErrorResponse>>(Arrays.asList(error), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * Whenever a "EmailMailException(500)" exception is thrown from service, the error response is mail not sent .
     */
    @ExceptionHandler(EmailException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<List<ErrorResponse>> handleMailException(EmailException ex) {

        ErrorResponse error = new ErrorResponse();
        error.setErrorType(ErrorConstants.INTERNAL_ERROR);
        error.setErrorMessage(
                StringUtils.isEmpty(ex.getMessage()) ? ErrorCode.MAIL_SERVER_ERROR.getMessage() : ex.getMessage());
        error.setErrorCode(ErrorCode.MAIL_SERVER_ERROR.getCode());
        LOGGER.debug(ex.getStackTrace().toString());
        return new ResponseEntity<List<ErrorResponse>>(Arrays.asList(error), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

