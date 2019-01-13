package com.wing.model;

import java.util.Objects;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SampleDescriptionParameter {
	@Size(max = 100)
	private final String description;
	
	public SampleDescriptionParameter(@JsonProperty("description") String description) {
		this.description = description;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return "SampleParameter{" +
				"description=" + description +
				"}";
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof SampleDescriptionParameter)) {
			return false;
		}
		
		SampleDescriptionParameter that = (SampleDescriptionParameter) o;
		
		return Objects.equals(this.description, that.description);
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(description);
	}
	
}
