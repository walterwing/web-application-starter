package com.wing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.wing.model.Sample;
import com.wing.service.SampleService;

/**
 * Sample controller.
 * 
 * @author Wing
 *
 */
@RestController
@RequestMapping("/api/samples")
public class SampleController {
	
	private Logger logger = LoggerFactory.getLogger(SampleController.class);

	@Autowired
	SampleService sampleService;
	
	@GetMapping(value="/{sampleValue}")
	public ResponseEntity<Sample> getSampleByValue(@PathVariable String sampleValue) {
		final String methodName = "getSampleByValue";
		logger.debug("Enter {} with sampleValue: {}", methodName, sampleValue);
		
		Sample sample = sampleService.getSampleByValue(sampleValue);
		
		logger.debug("Exit {} with sample: {}", methodName, sample);
		
		if (sample != null) {
			return ResponseEntity.ok(sample);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/{sampleValue}")
	public ResponseEntity<Sample> createNewSample(@PathVariable String sampleValue) {
		final String methodName = "createNewSample";
		logger.debug("Enter {} with sampleValue: {}", methodName, sampleValue);

		Sample sample = sampleService.createSample(sampleValue);

		logger.debug("Exit {} with created sample: {}", methodName, sample);

		return ResponseEntity
				.created(UriComponentsBuilder.fromPath("/api/samples/" + sample.getValue()).build().toUri())
				.body(sample);
	}
}
