package com.adrianm.jwttokenstudy.services;

import com.adrianm.jwttokenstudy.model.Role;
import com.adrianm.jwttokenstudy.model.repository.RolesRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RolesRepository rolesRepository;

    public Role createRole(Role roleToCreate) {
        Example roleExample = Example.of(Role
                .builder()
                .name(roleToCreate.getName())
                .build());

        if (rolesRepository.exists(roleExample)) {
            throw new RuntimeException("Ja existe um papel com este nome");
        }

        return rolesRepository.insert(roleToCreate);
    }

    public Optional<Role> findRoleById(String id) {
        return rolesRepository.findById(id);
    }

    public RoleService(final RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }
}
