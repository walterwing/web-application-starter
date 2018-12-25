package com.wing.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.wing.model.Sample;
import com.wing.repository.SampleRepository;

/**
 * Test {@link SampleService}
 * 
 * @author Wing
 *
 */
@RunWith(SpringRunner.class)
public class SampleServiceImplTest {

	@TestConfiguration
	static class SampleServiceImplTestConfiguration {
		@Bean
		public SampleService sampleService() {
			return new SampleServiceImpl();
		}
	}

	@Autowired
	SampleService sampleService;

	@MockBean
	SampleRepository sampleRepository;

	@Test
	public void givenExistingSampleId_whenGetSampleById_thenShouldReturnSample() {
		Long sampleId = 1L;
		String sampleValue = "sample1";

		BDDMockito.given(sampleRepository.findById(sampleId)).willReturn(Optional.of(new Sample(sampleValue)));

		Optional<Sample> sample = sampleService.getSampleById(sampleId);

		BDDMockito.then(sampleRepository).should().findById(sampleId);

		Assertions.assertThat(sample).isPresent();
		Assertions.assertThat(sample.get().getValue()).isEqualTo(sampleValue);
	}

	@Test
	public void givenMissingSampleId_whenGetSampleById_thenShouldReturnEmpty() {
		Long sampleId = 1L;

		BDDMockito.given(sampleRepository.findById(sampleId)).willReturn(Optional.empty());

		Optional<Sample> sample = sampleService.getSampleById(sampleId);

		BDDMockito.then(sampleRepository).should().findById(sampleId);

		Assertions.assertThat(sample).isEmpty();
	}

	@Test
	public void whencreateSample_thenShouldReturnCreatedSample() {
		String sampleValue = "sample1";
		Sample sample = new Sample(sampleValue);

		BDDMockito.given(sampleRepository.save(sample)).willReturn(sample);

		Sample createdSample = sampleService.createSample(sampleValue);

		BDDMockito.then(sampleRepository).should().save(sample);

		Assertions.assertThat(createdSample.getValue()).isEqualTo(sampleValue);
	}

	@Test
	public void givenExistingSampleValue_whenFindSamplesContainValue_thenShouldReturnSamples() {
		String sampleValue = "sample";

		String value1 = "sample1";
		String value2 = "2 sample";

		Sample sample1 = new Sample(value1);
		Sample sample2 = new Sample(value2);

		BDDMockito.given(sampleRepository.findByValueContains(sampleValue)).willReturn(Arrays.asList(sample1, sample2));

		List<Sample> samples = sampleService.findSamplesContainValue(sampleValue);

		BDDMockito.then(sampleRepository).should().findByValueContains(sampleValue);

		Assertions.assertThat(samples).size().isEqualTo(2);
		Assertions.assertThat(samples.stream().map(s -> s.getValue())).hasSameElementsAs(Arrays.asList(value1, value2));
	}
	
	@Test
	public void givenMissingSampleValue_whenFindSamplesContainValue_thenShouldReturnEmpty() {
		String sampleValue = "DOES_NOT_EXIST";

		BDDMockito.given(sampleRepository.findByValueContains(sampleValue)).willReturn(Collections.emptyList());

		List<Sample> samples = sampleService.findSamplesContainValue(sampleValue);

		BDDMockito.then(sampleRepository).should().findByValueContains(sampleValue);

		Assertions.assertThat(samples).isEmpty();
	}

}
