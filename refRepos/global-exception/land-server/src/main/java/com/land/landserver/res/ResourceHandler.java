package com.land.landserver.res;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Karuthiruman.Karthikeyan
 * @since 1.0.0
 * @Feb 12, 2019 11:43:00 AM
 **/

@Component
@Configuration
//@PropertySource(value = {"classpath:application.properties"})
public class ResourceHandler {
    
	private static final String CAS_URL_LOGIN 		= "spring.security.user.password";
	
	@Autowired
	private Environment environment;
	
//    Logger logger = LoggerFactory.getLogger(ResourceHandler.class);
    
    @Bean
    public String readProps() {
	 String user   = environment.getProperty(CAS_URL_LOGIN);
	 return user;
    }

    public static void main(String[] args) {
		
    	ResourceHandler rh = new ResourceHandler();
    	System.out.println(rh.readProps());
	}
    
//	public Path fileUpload(MultipartFile file) {
//		
//		Environment env;
//		FileReader urlPropertyFile = null;
//		Path path = null;
//		try {
//			
//			path = Paths.get(getClass()
//						.getClassLoader()
//				        .getResource("application.properties")
//				        .toURI());
//			urlPropertyFile = new FileReader(path.toString());
//		} catch (Exception e) {
//			logger.error("Error reading file");
//			e.printStackTrace();
//		}
//		Properties urlProperties = new Properties();
//		Path destinationPath = null;
//		try {
//			
//			urlProperties.load(urlPropertyFile);
//			//File extension fetched
//			String fileType = file.getContentType();
//			byte[] toByteArray = file.getBytes();
//			
//			if(fileType.contains("image")) {
//				
//			    //Image size is restricted to 5MB
//				if(file.getSize() > 5242880) {
//					logger.error("Image size exceeded than maximum size");
//				}
//				
//				destinationPath = Paths.get(
//					 					   urlProperties.getProperty("destinationUrlForImage")
//					 					   + "\\" 
//					 					   + file.getOriginalFilename()
//					 					   );
//				} else if(fileType.contains("video")) {
//					
//					destinationPath = Paths.get(
//						 					   urlProperties.getProperty("destinationUrlForVideo")
//						 					   + "\\" 
//						 					   + file.getOriginalFilename()
//						 					   );
//				} else if(fileType.contains("application")) {
//					
//					destinationPath = Paths.get(
//						 					   urlProperties.getProperty("destinationUrlForFile")
//						 					   + "\\" 
//						 					   + file.getOriginalFilename()
//						 					   );
//				} else {
//					logger.error("Unsupported file format error");
//				}
//		        Files.write(destinationPath, toByteArray);
//		} catch (IOException e) {
//			logger.error("Error saving file to destination");
//			e.printStackTrace();
//		}
//		return destinationPath;
//	}
}
