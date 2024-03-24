package com.adrianm.jwttokenstudy.rest.controllers;

import com.adrianm.jwttokenstudy.rest.controllers.dto.AuthLoginDTO;
import com.adrianm.jwttokenstudy.services.AuthenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public String authenticate(@RequestBody AuthLoginDTO authLoginDTO) throws JsonProcessingException {
        return authenticationService.userToken(authLoginDTO);
    }

    @Autowired
    public AuthController(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
}
