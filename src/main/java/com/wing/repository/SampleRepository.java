package com.wing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wing.model.Sample;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {
	
	/**
	 * Find Sample by its value.
	 * 
	 * @param value Sample value
	 * @return Sample object.
	 */
	public Sample findByValue(String value);
}
