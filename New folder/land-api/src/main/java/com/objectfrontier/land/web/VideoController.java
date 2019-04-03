package com.objectfrontier.land.web;

import static org.springframework.http.HttpHeaders.CONTENT_LENGTH;
import static org.springframework.http.HttpHeaders.CONTENT_RANGE;
import static org.springframework.http.HttpHeaders.RANGE;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.objectfrontier.land.common.exception.FileUploadException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.common.utility.Range;
import com.objectfrontier.land.dto.MediaDTO;
import com.objectfrontier.land.service.impl.MediaServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author mani.chellapandian
 * @since v1.0
 * Controller that manages video streaming. 
 */
@RestController
@RequestMapping("/api/v1/video")
//@CrossOrigin(value = "*")
@Api(value = "/api/v1/video")
public class VideoController {

	@Autowired
	MediaServiceImpl mediaService;
	
	private final String HYPHEN = "-";
	private final String COMMA = ",";
	private final String FORWARD_SLASH = "/";
	private final String BYTES = "bytes ";
	
	Logger logger = LoggerFactory.getLogger(VideoController.class);
	
	@ApiOperation(value = "findVideoById",
                  notes = "Find a video based on the id")
	@GetMapping(value = "/{id}")
	public void findVideoById(@PathVariable("id") long id, 
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {

		MediaDTO media = mediaService.read(id);
		Path filePath = Paths.get(media.getMediaPath());
		serveResource(filePath, request, response);
	}

	private void serveResource(Path filePath, 
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {

		Long length = null;
		File inputFile = new File(filePath.toString());
		length = inputFile.length();
		inputFile = null; // To allow the file to be garbage collected after size calculation
		Range full = new Range(0, length - 1, length);
		List<Range> ranges = new ArrayList<>();

		String range = request.getHeader(RANGE);
		if (range != null) {

			if (ranges.isEmpty()) {
				for (String part : range.substring(6).split(COMMA)) {

					long start = Range.sublong(part, 0, part.indexOf(HYPHEN));
					long end = Range.sublong(part, part.indexOf(HYPHEN) + 1, part.length());

					if (start == -1) {
						start = length - end;
						end = length - 1;
					} else if (end == -1 || end > length - 1) {
						end = length - 1;
					}
					ranges.add(new Range(start, end, length));
				}
			}
		}
		
		String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		
		try (InputStream input = new BufferedInputStream(Files.newInputStream(filePath));
		     OutputStream output = response.getOutputStream()) {
			Range rangeValue = ranges.get(0);

			final String fullConentRange = new StringBuilder(BYTES)
					.append(full.start)
					.append(HYPHEN)
					.append(full.end)
					.append(FORWARD_SLASH)
					.append(full.total).toString();
			
			final String partialContentRange = new StringBuilder(BYTES)
					.append(rangeValue.start)
					.append(HYPHEN)
					.append(rangeValue.end)
					.append(FORWARD_SLASH)
					.append(rangeValue.total).toString();

			if (ranges.isEmpty() || rangeValue == full) {
				response.setContentType(contentType);
				response.setHeader(CONTENT_RANGE, fullConentRange);
				response.setHeader(CONTENT_LENGTH, String.valueOf(full.length));
				Range.copy(input, output, length, full.start, full.length);

			} else if (ranges.size() == 1) {

				response.setContentType(contentType);
				response.setHeader(CONTENT_RANGE,
						partialContentRange);
				response.setHeader(CONTENT_LENGTH, String.valueOf(rangeValue.length));
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); // 206.
				Range.copy(input, output, length, rangeValue.start, rangeValue.length);
			}
		} catch (IOException e) {
			throw new FileUploadException(ErrorCode.VIDEO_ENCODE_ERROR);
		}
	}
}
