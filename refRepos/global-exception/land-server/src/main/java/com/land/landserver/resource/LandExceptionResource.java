package com.land.landserver.resource;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.land.landserver.exception.PreConditionFailException;
import com.land.landserver.exception.ResourceNotFoundException;

import io.micrometer.core.instrument.util.StringUtils;
@RestController
@RequestMapping("land")
public class LandExceptionResource {
	
	@GetMapping("/getGlobalException")
	public ResponseEntity<?> test() {
		throw new PreConditionFailException();
	}
	
	@GetMapping("/getGlobalException/{id}")
	public ResponseEntity<?> test(@PathVariable("id") long id) {
		if (id > 0) {
			throw new ResourceNotFoundException(String.format("Requested id with %d is not found", id));
		}
		throw new ResourceNotFoundException();
	}
	
	@GetMapping("/getGlobalException/internal-error")
	public ResponseEntity<?> test_internal_error() throws Exception {
		throw new Exception();
	}
	
	@GetMapping("/getGlobalException/not-found/message")
	public ResponseEntity<?> test_not_found(@PathParam("errorMessage") String errorMessage) {
		if (StringUtils.isEmpty(errorMessage)) {
			throw new ResourceNotFoundException();
		} else {
			throw new ResourceNotFoundException(errorMessage);
		}
	}
}
