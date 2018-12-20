package com.wing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wing.model.Sample;
import com.wing.service.SampleService;

/**
 * Sample controller.
 * 
 * @author Wing
 *
 */
@RestController
@RequestMapping("/api")
public class SampleController {
	
	private Logger logger = LoggerFactory.getLogger(SampleController.class);

	@Autowired
	SampleService sampleService;
	
	@GetMapping("/samples/{sampleValue}")
	public Sample getSampleByValue(@PathVariable String sampleValue) {
		final String methodName = "getSampleByValue";
		logger.debug("Enter {} with sampleValue: {}", methodName, sampleValue);
		
		Sample sample = sampleService.getSampleByValue(sampleValue);
		
		logger.debug("Exit {} with sample: {}", methodName, sample);
		
		return sample;
	}
	
	@PostMapping("/samples/{sampleValue}")
	@ResponseStatus(HttpStatus.CREATED)
	public void createNewSample(@PathVariable String sampleValue) {
		final String methodName = "createNewSample";
		logger.debug("Enter {} with sampleValue: {}", methodName, sampleValue);
		
		sampleService.createSample(sampleValue);
		
		logger.debug("Exit {}", methodName);
	}
}
