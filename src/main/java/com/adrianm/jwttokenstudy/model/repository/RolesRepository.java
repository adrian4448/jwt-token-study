package com.adrianm.jwttokenstudy.model.repository;

import com.adrianm.jwttokenstudy.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RolesRepository extends MongoRepository<Role, String> {
}
