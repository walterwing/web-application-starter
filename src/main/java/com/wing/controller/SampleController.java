package com.wing.controller;

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

	@Autowired
	SampleService sampleService;
	
	@GetMapping("/samples/{sampleId}")
	public Sample getSampleById(@PathVariable String sampleId) {
		Sample sample = sampleService.getSampleById(sampleId);
		
		return sample;
	}
}
