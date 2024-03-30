package com.adrianm.jwttokenstudy.rest.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkRolesDTO implements Serializable {
    private List<String> rolesId;
}
