package com.objectfrontier.land.repository;

import com.objectfrontier.land.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gunasekaran.k
 * @since v1.0
 * <p>
 * This interface declares the DB operation for User entity
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public List<User> findAll();

    public void deleteById(Long id);
}
