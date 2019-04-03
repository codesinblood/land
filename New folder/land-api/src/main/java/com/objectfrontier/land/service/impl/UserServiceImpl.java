package com.objectfrontier.land.service.impl;

import com.objectfrontier.land.common.exception.ResourceNotFoundException;
import com.objectfrontier.land.common.exception.model.ErrorCode;
import com.objectfrontier.land.model.User;
import com.objectfrontier.land.repository.UserRepository;
import com.objectfrontier.land.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author gunasekaran.k
 * @since v1.0
 * <p>
 * Service class defines the CRUD operations for User entity
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User read(long id) {

        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    @Override
    public List<User> readAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User updatedUser) {

        User existingUser = userRepository.findById(updatedUser.getId()).get();
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setLastLoggedIn(updatedUser.getLastLoggedIn());
        existingUser.setActiveStatus(updatedUser.isActiveStatus());
        return userRepository.save(existingUser);
    }
}
