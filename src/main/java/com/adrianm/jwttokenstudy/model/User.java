package com.adrianm.jwttokenstudy.model;

import com.adrianm.jwttokenstudy.rest.controllers.dto.UserDTO;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

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

    @Field(name = "email")
    private String email;

    @DocumentReference
    private List<Role> roles;

    @CreatedDate
    private LocalDateTime createdAt;

    public static User of(UserDTO userDTO) {
        return User
                .builder()
                .userId(userDTO.getUserId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build();
    }
}
