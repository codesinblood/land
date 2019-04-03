package com.objectfrontier.land.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.model.OnlineTest;
import com.objectfrontier.land.repository.OnlineTestRepository;
import com.objectfrontier.land.service.OnlineTestService;

/**
 * @author karthik.n
 * @since v1.0
 */
@Service
public class OnlineTestServiceImpl implements OnlineTestService {

    @Autowired
    private OnlineTestRepository onlineTestRepository;

    @Override
    public OnlineTest create(OnlineTest onlineTest) {
        return onlineTestRepository.save(onlineTest);
    }

    @Override
    public OnlineTest update(OnlineTest onlineTest) {
        return onlineTestRepository.save(onlineTest);
    }

    @Override
    public void delete(long id) {
        onlineTestRepository.deleteById(id);
    }

    @Override
    public OnlineTest read(long id) {
        Optional<OnlineTest> onlineTest = onlineTestRepository.findById(id);
        return onlineTest.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
    }

}
