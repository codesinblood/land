/**
 * 
 */
package com.objectfrontier.land.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.objectfrontier.land.model.Reference;


/**
 * @author karthik.n
 * @since v1.0
 */
@Repository
public interface ReferenceRepository extends CrudRepository<Reference, Long> {

}
