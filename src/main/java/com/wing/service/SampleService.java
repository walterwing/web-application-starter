package com.wing.service;

import com.wing.model.Sample;

/**
 * Interface of Sample Service.
 * 
 * @author Wing
 *
 */
public interface SampleService {
	/**
	 * Get Sample object by value.
	 * 
	 * @param sampleId Sample value.
	 * @return The Sample object.
	 */
	public Sample getSampleByValue(String sampleValue);
	
	public void createSample(String sampleValue);
}
