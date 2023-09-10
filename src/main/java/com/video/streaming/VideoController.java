package com.video.streaming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/videos")
public class VideoController {

	@Autowired
	private StreamingService streamingService;

	@GetMapping("/{title}")
	public Mono<ResponseEntity<Resource>> streamVideo(@PathVariable String title) {
		return streamingService.getS3Object(title + ".mp4")
				.map(resource -> ResponseEntity.ok().contentType(MediaType.valueOf("video/mp4")) // Set the content type
						.body(resource));
	}

}
