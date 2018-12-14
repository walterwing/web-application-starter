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
	 * Get Sample object by id.
	 * 
	 * @param sampleId Sample id.
	 * @return The Sample object.
	 */
	public Sample getSampleById(String sampleId);
}
