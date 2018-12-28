package com.wing.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wing.model.Sample;
import com.wing.repository.SampleRepository;

/**
 * Sample service implementation.
 * 
 * Hint: @Transactional can be used at Service class or methods level if a
 * service layer method was modified to do multiple calls to repository methods
 * -- all the code would still execute inside a single transaction as the
 * repository’s inner transactions would simply join the outer one started at
 * the service layer.
 * 
 * @author Wing
 *
 */
@Service
public class SampleServiceImpl implements SampleService {

	private Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);

	@Autowired
	SampleRepository sampleRepository;

	@Override
	public Optional<Sample> getSampleById(Long id) {
		logger.debug("getSampleById: {}", id);

		Optional<Sample> sample = sampleRepository.findById(id);

		logger.debug("Found Sample: {}", sample);

		return sample;
	}

	@Override
	public Sample createSample(String sampleValue) {
		return sampleRepository.save(new Sample(sampleValue));

	}

	@Override
	public List<Sample> findSamplesContainValue(String value) {
		return sampleRepository.findByValueContains(value);
	}

	@Override
	public void deleteSampleById(Long id) {
		sampleRepository.deleteById(id);
	}
}
