package com.wing.entity;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.Column;
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

	@Size(min = 1, max = 10)
	private String value;

	@Size(max = 100)
	@Column(nullable = true)
	private String description;

	protected Sample() {

	}

	public Sample(String value, String description) {
		this.value = value;
		this.description = description;
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

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the description
	 */
	public Optional<String> getDescription() {
		return Optional.ofNullable(description);
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Sample id: " + id + ", value: " + value + ", description: " + description;
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
		
		return (Objects.equals(id, that.id) && Objects.equals(value, that.value) && Objects.equals(description, that.description));
	}
	
	@Override
	public int hashCode() {
		int result = Objects.hashCode(id);
		
		result = 31 * result + Objects.hashCode(value);
		
		result = 31 * result + Objects.hashCode(description);
		
		return result;
	}

}
