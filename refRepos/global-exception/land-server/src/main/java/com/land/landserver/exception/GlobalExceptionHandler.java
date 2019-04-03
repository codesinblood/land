package com.land.landserver.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.land.landserver.exception.model.ErrorInfo;
import com.land.landserver.exception.model.ErrorType;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * @author LB
 *
 */
@ControllerAdvice(basePackages = "com.land.landserver")
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<List<ErrorInfo>> handleAccessDeniedException(AccessDeniedException ex) {
		ErrorInfo error = new ErrorInfo();
		error.setTimeStamp(new Date());
		error.setErrorType(ErrorConstants.UNAUTHORIZED);
		error.setErrorMessage(
				StringUtils.isEmpty(ex.getMessage()) ? ErrorType.ACCESS_DENIED.getMessage() : ex.getMessage());
		error.setErrorCode(ErrorType.ACCESS_DENIED.getCode());
		LOGGER.debug(ex.getStackTrace().toString());
		return new ResponseEntity<List<ErrorInfo>>(Arrays.asList(error), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(PreConditionFailException.class)
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	public ResponseEntity<List<ErrorInfo>> handlePreConditionFailException(PreConditionFailException ex) {
		ErrorInfo error = new ErrorInfo();
		error.setTimeStamp(new Date());
		error.setErrorType(ErrorConstants.PRECONDITION_FAIL);
		error.setErrorMessage(
				StringUtils.isEmpty(ex.getMessage()) ? ErrorType.PRECONDITION_FAIL.getMessage() : ex.getMessage());
		error.setErrorCode(ErrorType.PRECONDITION_FAIL.getCode());
		LOGGER.debug(ex.getStackTrace().toString());
		return new ResponseEntity<List<ErrorInfo>>(Arrays.asList(error), HttpStatus.PRECONDITION_FAILED);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<List<ErrorInfo>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<ErrorInfo> errors = new ArrayList<ErrorInfo>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			ErrorInfo currentError = new ErrorInfo();
			currentError.setTimeStamp(new Date());
			currentError.setErrorType(ErrorConstants.REQUEST_ERROR);
			currentError.setErrorMessage(error.getDefaultMessage());
			currentError.setErrorCode(ErrorType.METHOD_ARGUMENT_NOT_VALID.getCode());
			errors.add(currentError);
		}
		LOGGER.debug(ex.getStackTrace().toString());
		return new ResponseEntity<List<ErrorInfo>>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidRequestBodyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<List<ErrorInfo>> handleInvalidRequestBodyExceptions(InvalidRequestBodyException ex) {
		ErrorInfo error = new ErrorInfo();
		error.setTimeStamp(new Date());
		error.setErrorType(ErrorConstants.REQUEST_ERROR);
		error.setErrorMessage(
				StringUtils.isEmpty(ex.getMessage()) ? ErrorType.INVALID_REQUEST_BODY.getMessage() : ex.getMessage());
		error.setErrorCode(ErrorType.INVALID_REQUEST_BODY.getCode());
		LOGGER.debug(ex.getStackTrace().toString());
		return new ResponseEntity<List<ErrorInfo>>(Arrays.asList(error), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ResponseEntity<List<ErrorInfo>> handleResourceExistsException(ResourceExistsException ex) {
		ErrorInfo error = new ErrorInfo();
		error.setTimeStamp(new Date());
		error.setErrorType(ErrorConstants.RESOURCE_EXITS);
		error.setErrorMessage(
				StringUtils.isEmpty(ex.getMessage()) ? ErrorType.RESOURCE_EXITS.getMessage() : ex.getMessage());
		error.setErrorCode(ErrorType.RESOURCE_EXITS.getCode());
		LOGGER.debug(ex.getStackTrace().toString());
		return new ResponseEntity<List<ErrorInfo>>(Arrays.asList(error), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<List<ErrorInfo>> handleRecordNotFoundException(ResourceNotFoundException ex) {
		ErrorInfo error = new ErrorInfo();
		error.setTimeStamp(new Date());
		error.setErrorType(ErrorConstants.NOT_FOUND);
		error.setErrorMessage(
				StringUtils.isEmpty(ex.getMessage()) ? ErrorType.RESOURCE_NOT_FOUND.getMessage() : ex.getMessage());
		error.setErrorCode(ErrorType.RESOURCE_NOT_FOUND.getCode());
		LOGGER.debug(ex.getStackTrace().toString());
		return new ResponseEntity<List<ErrorInfo>>(Arrays.asList(error), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<List<ErrorInfo>> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException ex) {
		ErrorInfo error = new ErrorInfo();
		error.setTimeStamp(new Date());
		error.setErrorType(ErrorConstants.METHOD_TYPE_MISMATCH);
		error.setErrorMessage(
				StringUtils.isEmpty(ex.getMessage()) ? ErrorType.METHOD_TYPE_MISMATCH.getMessage() : ex.getMessage());
		error.setErrorCode(ErrorType.METHOD_TYPE_MISMATCH.getCode());
		LOGGER.debug(ex.getStackTrace().toString());
		return new ResponseEntity<List<ErrorInfo>>(Arrays.asList(error), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<List<ErrorInfo>> handleGenericException(Exception ex) {
		ErrorInfo error = new ErrorInfo();
		error.setTimeStamp(new Date());
		error.setErrorType(ErrorConstants.INTERNAL_ERROR);
		error.setErrorMessage(
				StringUtils.isEmpty(ex.getMessage()) ? ErrorType.INTERNAL_ERROR.getMessage() : ex.getMessage());
		error.setErrorCode(ErrorType.INTERNAL_ERROR.getCode());
		LOGGER.debug(ex.getStackTrace().toString());
		return new ResponseEntity<List<ErrorInfo>>(Arrays.asList(error), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
