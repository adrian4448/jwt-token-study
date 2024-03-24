package com.adrianm.jwttokenstudy.rest.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginDTO implements Serializable {
    private String username;
    private String password;
}
