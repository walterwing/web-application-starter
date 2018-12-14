package com.wing.service;

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

	public Sample getSampleById(String sampleId) {
		// For now simply return a new constructed sample
		// Feel free to change the impl (e.g. fetch sample by running query against some
		// kind of repository)
		Sample sample = new Sample();
		sample.setId(sampleId);
		sample.setValue("sample" + sampleId);

		return sample;
	}
}
