package com.objectfrontier.land.common.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.objectfrontier.land.common.exception.FileUploadException;
import com.objectfrontier.land.common.exception.InvalidRequestBodyException;
import com.objectfrontier.land.common.exception.model.ErrorCode;

/**
 * @author Sriram Narayanan, hariraj.sampath
 * @Feb 12, 2019
 * @since 1.0.0
 **/
@Configuration
public class ResourceHandler implements EnvironmentAware {

	static Logger logger = LoggerFactory.getLogger(ResourceHandler.class);

	private static Environment environment;

    private static final String IMAGE = "image"; 
    private static final String VIDEO = "video";
    private static final String APPLICATION = "application";
    private static final String TEXT = "text";
    private static final String LOCAL_FILE_NAME_PATTERN = "file.nameAttribute";
    private static final String IMAGE_STORAGE_DESTINATION = "destinationUrlForImage";
    private static final String VIDEO_STORAGE_DESTINATION = "destinationUrlForVideo";
    private static final String MISCFILES_STORAGE_DESTINATION = "destinationUrlForFile";
    private static String storageBasePath;

    /**
     * The local file storage destination path and the file conversion calls are made in this method.
     * This method returns the local file storage path.
     */
    public static String fileUpload(InputStream inputStream, String fileContent, String fileOriginalName) throws IOException {

        logger.info("Environment: {}", environment.toString());
		switch (getAbstractFileContentType(fileContent)) {
		case IMAGE:
			storageBasePath = environment.getProperty(IMAGE_STORAGE_DESTINATION);
			break;
		case VIDEO:
			storageBasePath = environment.getProperty(VIDEO_STORAGE_DESTINATION);
			break;
		case APPLICATION:
			storageBasePath = environment.getProperty(MISCFILES_STORAGE_DESTINATION);
			break;
		case TEXT:
			storageBasePath = environment.getProperty(MISCFILES_STORAGE_DESTINATION);
			break;
		default:
			throw new InvalidRequestBodyException(ErrorCode.UNSUPPORTED_FILE_FORMAT);
		}

        String localFileName = fileNameParser(fileOriginalName);
		String localStorageDestinationPath = new StringBuilder(storageBasePath)
				.append("\\")
				.append(localFileName).toString();
        return fileUploadToLocal(inputStream, localStorageDestinationPath);
    }

    /**
     * The local file is created with the parsed local storage path.
     * The input stream is written into the created file.
     */
    private static String fileUploadToLocal(InputStream inputStream, String localStoragePath) {

        try {
            FileUtils.copyInputStreamToFile(inputStream, new File(localStoragePath));
            return localStoragePath;
        } catch (Exception e) {
            throw new FileUploadException(ErrorCode.FILE_UPLOAD_ERROR, e);
        }
    }

    /**
     * The method is fed with the local path of the image file and returns the encoded String.
     * The fetched image file is encoded with Base64 scheme.
     */
    public static String imageEncoder(String storagePath) {

        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(storagePath));
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (Exception e) {
            throw new FileUploadException(ErrorCode.IMAGE_ENCODE_ERROR);
        }
    }
    
    /**
     * Method to handle file name parsing.
     * Returns the parsed name that will be set to the created local file.
     */
    private static String fileNameParser(String fileOriginalName) {

    	String dateFormat = environment.getProperty(LOCAL_FILE_NAME_PATTERN);
        SimpleDateFormat fileDate = new SimpleDateFormat(dateFormat);
        String localStorageFileName = new StringBuilder()
                                     .append(FilenameUtils.getBaseName(fileOriginalName))
                                     .append(fileDate.format(new Date()))
                                     .append(".")
                                     .append(FilenameUtils.getExtension(fileOriginalName))
                                     .toString();
        return localStorageFileName;
    }

	@Override
	public void setEnvironment(Environment env) {
		environment = env;
	}
    
    /**
     * Method to return the abstract MIME type. 
     */
    public static String getAbstractFileContentType(String fileContent) {
        
        String abstractFileContent[] = fileContent.split("/");
        return abstractFileContent[0];
    }
}
