package com.adrianm.jwttokenstudy.model;

import com.adrianm.jwttokenstudy.rest.controllers.dto.UserDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Field(name = "userId")
    private String userId;

    @Field(name = "password")
    private String password;

    @Field(name = "name")
    private String name;

    public static User of(UserDTO userDTO) {
        return User
                .builder()
                .userId(userDTO.getUserId())
                .name(userDTO.getName())
                .build();
    }
}
