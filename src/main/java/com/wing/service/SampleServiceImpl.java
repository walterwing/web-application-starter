package com.wing.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wing.model.Sample;
import com.wing.repository.SampleRepository;

/**
 * Sample service implementation.
 * 
 * @author Wing
 *
 */
@Service
@Transactional
public class SampleServiceImpl implements SampleService {
	
	private Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);
	
	@Autowired
	SampleRepository sampleRepository;
	
	@Override
	public Sample getSampleByValue(String value) {
		for (Sample sample : sampleRepository.findAll()) {
			logger.debug("-- {}", sample);
		}
		
		logger.debug("findByValue: {}", value);
		
		Sample sample = sampleRepository.findByValue(value);
		
		logger.debug("Found Sample: {}", sample);

		return sample;
	}

	@Override
	public Sample createSample(String sampleValue) {
		return sampleRepository.save(new Sample(sampleValue));
		
	}
}
