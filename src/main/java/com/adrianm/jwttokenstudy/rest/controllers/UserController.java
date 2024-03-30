package com.adrianm.jwttokenstudy.rest.controllers;

import com.adrianm.jwttokenstudy.model.Role;
import com.adrianm.jwttokenstudy.model.User;
import com.adrianm.jwttokenstudy.rest.controllers.dto.LinkRolesDTO;
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

    @PutMapping("roles/{id}")
    public void linkRole(@RequestBody LinkRolesDTO roles, @PathVariable String id) {
        userService.linkRoles(roles, id);
    }

    @GetMapping("roles/{id}")
    public List<Role> getUserRoles(@PathVariable String id) {
        return userService.getUserRoles(id);
    }

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }
}
