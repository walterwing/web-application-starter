package com.wing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * Log incoming requests. 
 * Note 1: this will NOT log response nor exception. 
 * Note 2: requires setting
 * "logging.level.org.springframework.web.filter.CommonsRequestLoggingFilter" as
 * DEBUG in application.properties
 * 
 * @author Wing
 *
 */
@Configuration
public class RequestLoggingFilterConfig {

	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();

		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setIncludeClientInfo(true);
		filter.setIncludeHeaders(true);

		filter.setMaxPayloadLength(10000);

		return filter;
	}
}
