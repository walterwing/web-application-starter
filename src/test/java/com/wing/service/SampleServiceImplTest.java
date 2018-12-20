package com.wing.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.wing.model.Sample;

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
	
	@Test
	public void whenValidSampleId_thenShouldReturnSample() {
		String sampleId = "1";
		Sample sample = sampleService.getSampleByValue(sampleId);
		
		assertThat(sample.getId(), is(sampleId));
		assertThat(sample.getValue(), is("sample1"));
	}
	
}
