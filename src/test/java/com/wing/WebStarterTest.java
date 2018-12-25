package com.wing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test class for {@link WebStarter}
 * 
 * @author Wing
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebStarter.class)
public class WebStarterTest {

	@Test
	public void application_shouldStartWithoutError() {
	}
}
