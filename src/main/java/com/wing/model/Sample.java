package com.wing.model;

/**
 * Sample model.
 * 
 * @author Wing
 *
 */
public class Sample {

	String id;
	String value;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Sample id: " + id + ", value: " + value;
	}
	
}
