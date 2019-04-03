package com.objectfrontier.land.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.model.Reference;
import com.objectfrontier.land.repository.ReferenceRepository;
import com.objectfrontier.land.service.ReferenceService;


/**
 * @author karthik.n
 * @since v1.0
 */
@Service
public class ReferenceServiceImpl implements ReferenceService {

    @Autowired
    private ReferenceRepository referenceRepository;

    @Override
    public Reference create(Reference reference) {
        return referenceRepository.save(reference);
    }

    @Override
    public Reference update(Reference reference) {
        return referenceRepository.save(reference);
    }

    @Override
    public void delete(long id) {
        referenceRepository.deleteById(id);
    }

    @Override
    public Reference read(long id) {
        Optional<Reference> reference = referenceRepository.findById(id);
        return reference.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
    }

}
