package com.objectfrontier.land.service;

import com.objectfrontier.land.model.User;

import java.util.List;

/**
 * @author gunasekaran.k
 * @since v1.0
 * <p>
 * Interface class that has the following methods for UserService
 */
public interface UserService {

    public User create(User user);

    public User read(long id);

    public List<User> readAll();

    public void delete(long id);

    public User update(User updatedUser);
}
