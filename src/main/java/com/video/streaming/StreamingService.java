package com.video.streaming;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import reactor.core.publisher.Mono;

@Service
public class StreamingService {

	@Value("${aws.s3.bucketName}") // Load your S3 bucket name from application.properties or application.yml
	private String bucketName;

	@Autowired
	private AmazonS3Client amazonS3Client;

	public Mono<Resource> getS3Object(String key) {
		GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);

		return Mono.fromSupplier(() -> {
			S3Object s3Object = amazonS3Client.getObject(getObjectRequest);
			try (InputStream objectData = s3Object.getObjectContent()) {
				byte[] data = objectData.readAllBytes();
				return new ByteArrayResource(data);
			} catch (IOException e) {
				throw new RuntimeException("Error reading S3 object", e);
			}
		});
	}
}
