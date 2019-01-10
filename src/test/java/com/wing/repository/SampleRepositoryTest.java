package com.wing.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wing.entity.Sample;

/**
 * Test {@link SampleRepository}
 * 
 * @author Wing
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SampleRepositoryTest {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private SampleRepository sampleRepository;
	
	private String sampleValue1 = "sample 1";
	private String sampleValue2 = "2 sample";
	

	@Before
	public void setUp() {
		Sample sample1 = new Sample(sampleValue1, null);
		Sample sample2 = new Sample(sampleValue2, null);
		
		entityManager.persist(sample1);
		entityManager.persist(sample2);
		entityManager.flush();
	}
	
	@Test
	public void givenExistingId_whenFindById_thenReturnSample() {
		Optional<Sample> sample = sampleRepository.findById(1L);
		
		Assertions.assertThat(sample).isPresent();
		Assertions.assertThat(sample.get().getValue()).isEqualTo(sampleValue1);
	}
	
	@Test
	public void givenMissingId_whenFindById_thenReturnEmpty() {
		Optional<Sample> sample = sampleRepository.findById(100L);
		
		Assertions.assertThat(sample).isEmpty();
	}
	
	@Test
	public void givenExistingValue_whenFindByValueContains_thenReturnSamples() {
		String sampleValue = "sample";
		List<Sample> samples = sampleRepository.findByValueContains(sampleValue);
		
		Assertions.assertThat(samples).size().isEqualTo(2);
		Assertions.assertThat(samples.stream().map(s -> s.getValue())).hasSameElementsAs(Arrays.asList(sampleValue1, sampleValue2));
	}
	
	@Test
	public void givenMissingValue_whenFindByValueContains_thenReturnEmpty() {
		String sampleValue = "DOES_NOT_EXIST";
		List<Sample> samples = sampleRepository.findByValueContains(sampleValue);
		
		Assertions.assertThat(samples).isEmpty();
	}
	
}
