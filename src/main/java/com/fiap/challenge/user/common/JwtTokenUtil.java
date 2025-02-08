package com.fiap.challenge.user.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.fiap.challenge.user.core.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	@Value("${jwt.secret}")
	private String secret;

	public String generateJwtToekn(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.create()
					.withSubject(user.getName())
					.withClaim("id", user.getId().toString())
					.withClaim("email", user.getEmail())
					.sign(algorithm);
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro ao gerar token!", e);
		}
	}

}