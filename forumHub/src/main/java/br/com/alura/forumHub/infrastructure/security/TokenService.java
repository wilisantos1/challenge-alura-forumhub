package br.com.alura.forumHub.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import br.com.alura.forumHub.application.entities.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String key;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(key);

          return JWT.create()
                    .withIssuer("ForumHub")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(key);
            return JWT.require(algoritmo)
                    .withIssuer("ForumHub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            System.err.println("Erro ao verificar token JWT: " + exception.getMessage());
            throw new RuntimeException("Token inválido!", exception);
        }
    }


    private Instant dataExpiracao() {
        return LocalDateTime.now()
            .plusHours(2)
            .toInstant(ZoneOffset.ofHours(-3));
    }
}
