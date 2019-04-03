package com.objectfrontier.land.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.objectfrontier.land.model.Evaluation;

/**
 * @author isaac.murugaian
 * @since v1.0
 */

@Repository
public interface EvaluationRepository extends CrudRepository<Evaluation, Long> {

}
