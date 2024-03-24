package com.adrianm.jwttokenstudy.rest.controllers;

import com.adrianm.jwttokenstudy.model.User;
import com.adrianm.jwttokenstudy.rest.controllers.dto.UserDTO;
import com.adrianm.jwttokenstudy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User userToCreate) {
        return userService.createUser(userToCreate);
    }

    @GetMapping("{userId}")
    public User getUserById(@PathVariable String userId) {
        return userService.findUserById(userId).get();
    }

    @GetMapping
    public List<User> getUsers(UserDTO user) {
        return userService.getUsers(User.of(user));
    }

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }
}
