package com.adrianm.jwttokenstudy.model.repository;

import com.adrianm.jwttokenstudy.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User, String> {
}
