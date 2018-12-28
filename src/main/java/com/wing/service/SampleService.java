package com.wing.service;

import java.util.List;
import java.util.Optional;

import com.wing.model.Sample;

/**
 * Interface of Sample Service.
 * 
 * @author Wing
 *
 */
public interface SampleService {
	/**
	 * Get Sample object by ID.
	 * 
	 * @param id Sample ID.
	 * @return The Sample object.
	 */
	public Optional<Sample> getSampleById(Long id);
	
	/**
	 * Create a new Sample
	 * @param value Sample value.
	 * @return The created Sample object.
	 */
	public Sample createSample(String value);
	
	/**
	 * Find all samples whose value contains the specified value.
	 * 
	 * @param value Specified value to look for.
	 * @return Matched samples.
	 */
	public List<Sample> findSamplesContainValue(String value);
	
	/**
	 * Delete Sample by ID.
	 * 
	 * @param id Sample ID.
	 */
	public void deleteSampleById(Long id);
}
