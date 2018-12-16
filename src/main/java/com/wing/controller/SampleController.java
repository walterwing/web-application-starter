package com.wing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("/samples/{sampleId}")
	public Sample getSampleById(@PathVariable String sampleId) {
		final String methodName = "getSampleById";
		logger.debug("Enter {} with id: {}", methodName, sampleId);
		
		Sample sample = sampleService.getSampleById(sampleId);
		
		logger.debug("Exit {} with sample: {}", methodName, sample);
		
		return sample;
	}
}
