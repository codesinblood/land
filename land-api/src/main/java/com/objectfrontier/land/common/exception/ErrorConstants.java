package com.objectfrontier.land.common.exception;

/**
 * @author LB, Sriram Narayanan
 */
public class ErrorConstants {

    public static final String NOT_FOUND = "Request not found";
    public static final String REQUEST_ERROR = "Invalid/Missing Request Body";
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String PRECONDITION_FAIL = "Condition Failed";
    public static final String INTERNAL_ERROR = "Internal Error";
    public static final String RESOURCE_EXITS = "Conflict";
    public static final String METHOD_TYPE_MISMATCH = "Invalid Path Param";

    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong Internal Error";
    public static final String ACCESS_DENIED_ERROR_MESSAGE = "Access denied";
    public static final String PRECONDITION_FAILED_ERROR_MESSAGE = "Resource execution condition is failed";
    public static final String METHOD_ARGUMENT_NOT_VALID_ERROR_MESSAGE = "Provided arguments are not matching with the resource execution";
    public static final String INVALID_REQUEST_BODY_ERROR_MESSAGE = "Provided request body is not matching with the resource execution";
    public static final String RESOURCE_EXITS_ERROR_MESSAGE = "Provided data is being already stored or updated in the datasource";
    public static final String RESOURCE_NOT_FOUND_ERROR_MESSAGE = "Requested resource by reference is not found in the datasource";
    public static final String METHOD_TYPE_MISMATCH_ERROR_MESSAGE = "Requested resource in the path variable is invalid";
    public static final String MAIL_SERVER_ERROR = "Error in sending mail";
    public static final String IMAGE_SIZE_LIMIT_EXCEEDED = "File size greater than 5MB";
    public static final String FILE_UPLOAD_ERROR = "Error while uploading file";
    public static final String UNSUPPORTED_FILE_FORMAT = "Unsupported file format";
    public static final String IMAGE_ENCODE_ERROR = "Error while encoding image";

}
