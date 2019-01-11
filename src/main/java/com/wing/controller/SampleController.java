package com.wing.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.wing.entity.Sample;
import com.wing.model.SampleParameter;
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

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sample> createNewSample(@RequestBody @Valid SampleParameter sampleParameter) {
		final String methodName = "createNewSample";
		logger.debug("Enter {} with value: {}", methodName, sampleParameter);

		Sample sample = sampleService.createSample(sampleParameter.getValue(), sampleParameter.getDescription());

		logger.debug("Exit {} with created sample: {}", methodName, sample);

		return ResponseEntity
				.created(UriComponentsBuilder.fromPath("/api/samples/" + sample.getId()).build().toUri())
				.body(sample);
	}

	@GetMapping
	public ResponseEntity<List<Sample>> searchSamplesByValue(@RequestParam(value = "q", required = true) String query) {
		final String methodName = "searchSamplesByValue";
		logger.debug("Enter {} with value: {}", methodName, query);

		List<Sample> sampleList = sampleService.findSamplesContainValue(query);

		logger.debug("Exit {} with created sample: {}", methodName, sampleList);

		if (!sampleList.isEmpty()) {
			return ResponseEntity.ok(sampleList);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteSampleById(@PathVariable Long id) {
		final String methodName = "deleteSampleById";
		logger.debug("Enter {} with id: {}", methodName, id);

		sampleService.deleteSampleById(id);

		logger.debug("Exit {}", methodName);
		
		return ResponseEntity.noContent().build();
	}
}
