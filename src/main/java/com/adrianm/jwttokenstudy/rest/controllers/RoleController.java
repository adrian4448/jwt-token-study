package com.adrianm.jwttokenstudy.rest.controllers;

import com.adrianm.jwttokenstudy.model.Role;
import com.adrianm.jwttokenstudy.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @GetMapping("{id}")
    public Role findRoleById(@PathVariable String id) {
        return roleService.findRoleById(id).orElseThrow(() ->
            new RuntimeException("Nao existe nenhum papel com este ID"));
    }

    public RoleController(final RoleService roleService) {
        this.roleService = roleService;
    }
}
