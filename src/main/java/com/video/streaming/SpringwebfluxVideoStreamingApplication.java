package com.video.streaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringwebfluxVideoStreamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringwebfluxVideoStreamingApplication.class, args);
	}

}
