package com.objectfrontier.land.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.objectfrontier.land.model.OnlineTest;

/**
 * @author karthik.n
 * @since v1.0
 */

@Repository
public interface OnlineTestRepository extends CrudRepository<OnlineTest, Long> {

}
