package com.wing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wing.model.Sample;

/**
 * @Repository is to enable exception translation from JPA exceptions to
 *             Spring’s DataAccessException hierarchy.
 * 
 * @Transactional By default, CRUD methods on repository instances are
 *                transactional. For read operations, the transaction
 *                configuration readOnly flag is set to true. All others are
 *                configured with a plain @Transactional so that default
 *                transaction configuration applies.
 * @author Wing
 *
 */
@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {

	/**
	 * Find Sample by its id.
	 * 
	 * Hint 1: query will be derived from method name by Spring Data.
	 * 
	 * Hint 2: no need to annotate @Transactional(readOnly = true) since that's
	 * configured by default for the read operation -- refer to the code of
	 * SimpleJpaRepository.
	 * 
	 * @param value Sample ID
	 * @return Sample object.
	 */
//	public Optional<Sample> findById(Long id);

	/**
	 * Find Samples that contain specified value.
	 * 
	 * @Query and @NamedQuery are two kinds of named/declared query.
	 * 
	 * @Param is to give a method parameter a concrete name and bind the name in the
	 *        query.
	 * 
	 * @param value
	 * @return
	 */
	@Query("select s from Sample s where s.value like %:value%")
	public List<Sample> findByValueContains(@Param("value") String value);
}
