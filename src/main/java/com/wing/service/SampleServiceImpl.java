package com.wing.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wing.model.Sample;

/**
 * Sample service implementation.
 * 
 * @author Wing
 *
 */
@Service
public class SampleServiceImpl implements SampleService {
	
	private Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);

	public Sample getSampleById(String sampleId) {
		// For now simply return a new constructed sample
		// Feel free to change the impl (e.g. fetch sample by running query against some
		// kind of repository)
		Sample sample = new Sample();
		sample.setId(sampleId);
		sample.setValue("sample" + sampleId);
		
		logger.debug("Created Sample: {}", sample);

		return sample;
	}
}
