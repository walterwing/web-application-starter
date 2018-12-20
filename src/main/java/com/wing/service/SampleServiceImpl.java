package com.wing.service;

import javax.persistence.EntityManager;
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
	
	@Autowired
	EntityManager entityManager;

	@Override
	public Sample getSampleByValue(String value) {
		Sample sample = sampleRepository.findByValue(value);
		
		logger.debug("Found Sample: {}", sample);

		return null;
	}

	@Override
	public void createSample(String sampleValue) {
		Sample sample = new Sample("sample1");
		entityManager.persist(sample);
		entityManager.flush();
	}
}
