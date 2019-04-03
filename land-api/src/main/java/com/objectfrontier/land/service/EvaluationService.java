package com.objectfrontier.land.service;

import com.objectfrontier.land.model.Evaluation;

/**
 * @author karthik.n
 * 
 */

public interface EvaluationService {

	public Evaluation create(Evaluation evaluationParam);

	public Evaluation read(long id);

	public Evaluation update(Evaluation evaluationParam);

	public void delete(long id);
}
