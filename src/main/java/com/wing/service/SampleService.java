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
	
	/**
	 * Create a new Sample
	 * @param sampleValue Sample value.
	 * @return The created Sample object.
	 */
	public Sample createSample(String sampleValue);
}
