package com.wing.model;

import java.util.Objects;

import javax.validation.constraints.Size;

public class SampleParameter {
	@Size(min = 1, max = 10)
	private final String value;
	
	@Size(max = 100)
	private final String description;
	
	public SampleParameter(String value, String description) {
		this.value = value;
		this.description = description;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
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
				"value=" + value + ", " +
				"description=" + description +
				"}";
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof SampleParameter)) {
			return false;
		}
		
		SampleParameter that = (SampleParameter) o;
		
		return Objects.equals(this.value, that.value) && Objects.equals(this.description, that.description);
	}
	
	@Override
	public int hashCode() {
		int result = Objects.hashCode(value);
		
		result = 31 * result + Objects.hashCode(description);
		
		return result;
	}
	
}
