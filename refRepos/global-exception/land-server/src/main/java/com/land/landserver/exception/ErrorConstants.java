/**
 * 
 */
package com.land.landserver.exception;

/**
 * @author LB
 *
 */
public class ErrorConstants {
	/**
	 * Error Type
	 */
	public static final String NOT_FOUND = "Request not found";
	public static final String REQUEST_ERROR = "Invalid/Missing Request Body";
	public static final String UNAUTHORIZED = "Unauthorized";
	public static final String PRECONDITION_FAIL = "Condition Failed";
	public static final String INTERNAL_ERROR = "Internal Error";
	public static final String RESOURCE_EXITS = "Conflict";
	public static final String METHOD_TYPE_MISMATCH = "Invalid Path Param";
	
	/**
	 * Error Type Messages
	 */
	public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong Internal Error";
	public static final String ACCESS_DENIED_ERROR_MESSAGE = "Access Denied";
	public static final String PRECONDITION_FAILED_ERROR_MESSAGE = 
			"Resource execution condition is failed";
	public static final String METHOD_ARGUMENT_NOT_VALID_ERROR_MESSAGE = 
			"Provided arguments are not matching with the resource execution";
	public static final String INVALID_REQUEST_BODY_ERROR_MESSAGE = 
			"Provided Request Body is not matching with the resource execution";
	public static final String RESOURCE_EXITS_ERROR_MESSAGE = 
			"Provided data is being already stored or updated in the datasource";
	public static final String RESOURCE_NOT_FOUND_ERROR_MESSAGE = 
			"Requested resource by reference is not found in the datasource";
	public static final String METHOD_TYPE_MISMATCH_ERROR_MESSAGE = 
			"Requested resource in the path variable is invalid";
}
