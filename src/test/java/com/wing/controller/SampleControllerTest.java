package com.wing.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.TransactionSystemException;

import com.wing.model.Sample;
import com.wing.service.SampleService;

/**
 * Test {@link SampleController}
 * 
 * @author Wing
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SampleService sampleService;

	@Test
	public void givenExistingId_whenGetSampleById_thenReturnSample() throws Exception {
		Long id = 1L;

		Sample sample = new Sample("sample3");

		BDDMockito.given(sampleService.getSampleById(id)).willReturn(Optional.of(sample));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/samples/" + id))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.value", CoreMatchers.is(sample.getValue())));

		BDDMockito.then(sampleService).should().getSampleById(id);
	}

	@Test
	public void givenMissingId_whenGetSampleById_thenReturn404() throws Exception {
		Long id = -1L;

		BDDMockito.given(sampleService.getSampleById(id)).willReturn(Optional.empty());

		mockMvc.perform(MockMvcRequestBuilders.get("/api/samples/" + id))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		BDDMockito.then(sampleService).should().getSampleById(id);
	}

	@Test
	public void whenCreateSample_thenReturnSampleAndLocation() throws Exception {
		String value = "sample3";

		Sample sample = new Sample(value);

		BDDMockito.given(sampleService.createSample(value)).willReturn(sample);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/samples/" + value))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.header().string("Location", CoreMatchers.is("/api/samples/null")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.value", CoreMatchers.is(value)));

		BDDMockito.then(sampleService).should().createSample(value);
	}
	
	@Test
	public void givenInvalidValue_whenCreateSample_thenReturn400() throws Exception {
		final String value = "a_very_long_value_that_exceeds_the_specified_limit";

		ConstraintViolation<?> cv = Mockito.mock(ConstraintViolation.class);
		ConstraintViolationException ce = new ConstraintViolationException(Stream.of(cv).collect(Collectors.toSet()));
		RollbackException re = new RollbackException("RollbackException", ce);
		TransactionSystemException te = new TransactionSystemException("TransactionSystemException", re);
		
		final String errorMsg = "bla bla";
		BDDMockito.given(cv.toString()).willReturn(errorMsg);
		
		BDDMockito.given(sampleService.createSample(value)).willThrow(te);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/samples/" + value))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.status().reason(CoreMatchers.containsString(errorMsg)))
				.andDo(MockMvcResultHandlers.print())
				.andDo(mvcResult -> {
					Exception exception = mvcResult.getResolvedException();
					Assert.assertEquals(TransactionSystemException.class, exception.getClass());
				});
		
		BDDMockito.then(sampleService).should().createSample(value);
	}

	@Test
	public void whenSearchSamplesByValue_thenReturnSamples() throws Exception {
		String query = "sample";

		String value1 = "sample1";
		String value2 = "2sample";

		Sample sample1 = new Sample(value1);
		Sample sample2 = new Sample(value2);
		List<Sample> samples = Arrays.asList(sample1, sample2);

		BDDMockito.given(sampleService.findSamplesContainValue(query)).willReturn(samples);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/samples?q=" + query))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$..value", Matchers.containsInAnyOrder(value1, value2)));

		BDDMockito.then(sampleService).should().findSamplesContainValue(query);
	}

	@Test
	public void whenSearchSamplesByValue_thenReturn404() throws Exception {
		String query = "sample";

		List<Sample> samples = Collections.emptyList();

		BDDMockito.given(sampleService.findSamplesContainValue(query)).willReturn(samples);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/samples?q=" + query))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		BDDMockito.then(sampleService).should().findSamplesContainValue(query);
	}

	@Test
	public void givenExistingId_whenDeleteSampleById_thenReturn204() throws Exception {
		Long id = 1L;

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/samples/" + id))
				.andExpect(MockMvcResultMatchers.status().isNoContent())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().string(""));

		BDDMockito.then(sampleService).should().deleteSampleById(id);
	}

	@Test
	public void givenNonexistingId_whenDeleteSampleById_thenReturn404() throws Exception {
		Long id = 1L;

		// mock exception for void method
		BDDMockito
				.willThrow(new EmptyResultDataAccessException(
						"No class com.wing.model.Sample entity with id " + id + " exists!", 1))
				.given(sampleService).deleteSampleById(id);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/samples/" + id))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andDo(MockMvcResultHandlers.print())
				.andDo(mvcResult -> {
					Exception exception = mvcResult.getResolvedException();
					Assert.assertEquals(EmptyResultDataAccessException.class, exception.getClass());
					Assert.assertEquals("No class com.wing.model.Sample entity with id " + id + " exists!", exception.getMessage());
				});

		BDDMockito.then(sampleService).should().deleteSampleById(id);
	}
}
