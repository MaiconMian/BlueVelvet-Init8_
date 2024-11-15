package com.bluevelvet.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bluevelvet.model.User;
import com.bluevelvet.model.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            List<Integer> roleIds = user.getRoles().stream()
                    .map(Role::getId)
                    .toList();

            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withClaim("roles", roleIds)
                    .withClaim("email", user.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generation token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getClaim("email")
                    .asString();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid or expired token", exception);
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime
                .now().plusHours(1)
                .toInstant(ZoneOffset.of("-03:00"));
    }

}
