package com.wing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wing.model.Sample;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {
	
	public Sample findByValue(String value);
}
