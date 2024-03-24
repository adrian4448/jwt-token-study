package com.adrianm.jwttokenstudy.services;

import com.adrianm.jwttokenstudy.model.User;
import com.adrianm.jwttokenstudy.rest.controllers.dto.AuthLoginDTO;
import com.adrianm.jwttokenstudy.services.pojo.TokenPayload;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Base64;

@Service
public class AuthenticationService {

    private final String privateKey;
    private final UserService userService;
    private final ObjectMapper mapper = new ObjectMapper();

    public String userToken(AuthLoginDTO authLoginDTO) throws JsonProcessingException {
        final User userToFind = User
                .builder()
                .userId(authLoginDTO.getUsername())
                .password(authLoginDTO.getPassword())
                .build();

        User user = userService.getUser(userToFind)
                .orElseThrow(() -> new RuntimeException("Nao foi possivel autenticar este usuario"));

        Algorithm algorithm = Algorithm.HMAC384(privateKey);
        String token = JWT.create()
                .withIssuer(user.getUserId())
                .withPayload(mapper.writer().writeValueAsString(buildTokenPayload(user.getUserId(), user.getId())))
                .withExpiresAt(Instant.from(Instant.now().atZone(ZoneId.of("America/Sao_Paulo"))).plusSeconds(600L))
                .sign(algorithm);

        return token;
    }

    public void verifyToken(String token) throws JsonProcessingException {
        DecodedJWT decodedToken = JWT.decode(token.replace("Bearer ", ""));

        boolean tokenExpired = decodedToken.getExpiresAtAsInstant()
                .isBefore(Instant.from(Instant.now().atZone(ZoneId.of("America/Sao_Paulo"))));

        if (tokenExpired) {
            throw new RuntimeException("O Token ja esta expirado");
        }

        String payloadJson = new String(Base64.getDecoder().decode(decodedToken.getPayload()));

        String encodedAuthenticationId = mapper
                .readTree(payloadJson).get("authenticationId").asText();

        String decodedAuthenticationId = new String(Base64.getDecoder().decode(encodedAuthenticationId));

        userService.findUserById(decodedAuthenticationId.split("/")[1])
                .orElseThrow(() -> new RuntimeException("Este token e invalido"));
    }

    private TokenPayload buildTokenPayload(String userId,
                                           String id) {
        String tokenPayload = Base64.getEncoder().encodeToString((userId + "/" + id).getBytes(StandardCharsets.UTF_8));

        return new TokenPayload(tokenPayload);
    }

    @Autowired
    public AuthenticationService(@Value("token.private-key") final String privateKey,
                                 final UserService userService) {
        this.privateKey = privateKey;
        this.userService = userService;
    }
}
