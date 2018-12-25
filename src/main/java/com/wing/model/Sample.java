package com.wing.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Sample model.
 * 
 * @Entity is needed for annotating a POJO class as an entity. Optionally you
 *         can specify the entity name, which by default will be the unqualified
 *         class name ("Sample" in this case). The name in @Entity is for JPA-QL
 *         queries.
 * 
 * @Table(name = "sample") is just to customize the name of the table. If you
 *             omit this annotation, the name of the table will be the name of
 *             the Entity.
 * 
 * @author Wing
 *
 */
@Entity
@Table(name = "sample")
public class Sample {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(min = 1, max = 100)
	private String value;

	protected Sample() {

	}

	public Sample(String value) {
		this.value = value;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Sample id: " + id + ", value: " + value;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof Sample)) {
			return false;
		}
		
		Sample that = (Sample) o;
		
		return (Objects.equals(id, that.id) && Objects.equals(value, that.value));
	}
	
	@Override
	public int hashCode() {
		int result = Objects.hashCode(id);
		
		result = 31 * result + Objects.hashCode(value);
		
		return result;
	}

}
