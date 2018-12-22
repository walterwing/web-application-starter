package com.wing.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping(value = "/{id}")
	public ResponseEntity<Sample> getSampleById(@PathVariable Long id) {
		final String methodName = "getSampleById";
		logger.debug("Enter {} with id: {}", methodName, id);

		Optional<Sample> sample = sampleService.getSampleById(id);

		logger.debug("Exit {} with sample: {}", methodName, sample);

		if (sample.isPresent()) {
			return ResponseEntity.ok(sample.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{value}")
	public ResponseEntity<Sample> createNewSample(@PathVariable String value) {
		final String methodName = "createNewSample";
		logger.debug("Enter {} with value: {}", methodName, value);

		Sample sample = sampleService.createSample(value);

		logger.debug("Exit {} with created sample: {}", methodName, sample);

		return ResponseEntity
				.created(UriComponentsBuilder.fromPath("/api/samples/" + sample.getValue()).build().toUri())
				.body(sample);
	}

	@GetMapping
	public ResponseEntity<List<Sample>> searchSamplesByValue(@RequestParam(value = "q", required = true) String query) {
		final String methodName = "searchSamplesByValue";
		logger.debug("Enter {} with value: {}", methodName, query);

		List<Sample> sampleList = sampleService.findSamplesContainValue(query);

		logger.debug("Exit {} with created sample: {}", methodName, sampleList);

		return ResponseEntity.ok(sampleList);
	}
}
