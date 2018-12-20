package com.wing.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

	// TODO: fix this failed test
	@Test
	public void givenSample_whenGetSampleById_thenReturnSample() throws Exception {
		Sample sample = new Sample("sample3");
		sample.setId(3L);

		given(sampleService.getSampleByValue("3")).willReturn(sample);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/samples/3"))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andExpect(jsonPath("$.id", is(sample.getId())))
			   .andExpect(jsonPath("$.value", is(sample.getValue())));
	}

}
