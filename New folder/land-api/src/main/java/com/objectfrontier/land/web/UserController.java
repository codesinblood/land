package com.objectfrontier.land.web;

import com.objectfrontier.land.model.User;
import com.objectfrontier.land.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gunasekaran.k
 * @since v1.0
 * <p>
 * This Controller manages the request and response for the UserService
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.create(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> readUser(@PathVariable("id") long id) {
        return new ResponseEntity<User>(userService.read(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> readAllUser() {
        return new ResponseEntity<List<User>>(userService.readAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.update(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(required = true) long id) {
        userService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
