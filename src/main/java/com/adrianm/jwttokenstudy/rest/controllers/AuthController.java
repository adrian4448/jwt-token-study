package com.adrianm.jwttokenstudy.rest.controllers;

import com.adrianm.jwttokenstudy.rest.controllers.dto.AuthLoginDTO;
import com.adrianm.jwttokenstudy.services.AuthenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public String authenticate(@RequestBody AuthLoginDTO authLoginDTO) throws JsonProcessingException {
        return authenticationService.userToken(authLoginDTO);
    }

    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    public void verifyToken(@RequestParam String token) throws JsonProcessingException {
        authenticationService.verifyToken(token);
    }

    @Autowired
    public AuthController(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
}
