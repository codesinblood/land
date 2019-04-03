package video;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoService
{

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
    @GetMapping("/course")
	    public void showVideo(@RequestParam(value="name") String name) 
	    {

	        String path = "/home/superadmin/Office/test.mp4";
	        try {
	            MultipartFile.fromFile(new File(path))
	            .with(request)
	            .with(response)
	            .serveResource();
	        } catch (Exception e) {

	        }
	    }
	
}
