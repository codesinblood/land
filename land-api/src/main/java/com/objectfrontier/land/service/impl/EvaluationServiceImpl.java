package com.objectfrontier.land.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.model.Evaluation;
import com.objectfrontier.land.repository.EvaluationRepository;
import com.objectfrontier.land.service.EvaluationService;

/**
 * @author isaac.murugaian
 * @since v1.0
 * Service class defines the CRUD operations for User entity
 */
@Service
public class EvaluationServiceImpl implements EvaluationService{ 


	@Autowired
	private EvaluationRepository evaluationRepository;

	@Override
	public Evaluation create(Evaluation evaluation) {
		return evaluationRepository.save(evaluation);
	}

	@Override
	public Evaluation read(long id) {
		Optional<Evaluation> user = evaluationRepository.findById(id);
		return user.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
	}

	@Override
	public Evaluation update(Evaluation updatedEvaluation) {
		return evaluationRepository.save(updatedEvaluation);
	}

	@Override
    public void delete(long id) {
        evaluationRepository.deleteById(id);
    }
}
