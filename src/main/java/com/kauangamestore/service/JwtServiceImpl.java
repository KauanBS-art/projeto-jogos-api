package com.kauangamestore.service;

import com.kauangamestore.model.Usuario;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(Usuario usuario) {
        Instant expiryDate = Instant.now().plus(EXPIRATION_TIME);

        Set<String> roles = new HashSet<>();
        // O Quarkus Security lê os grupos/roles para validar o @RolesAllowed
        roles.add(usuario.getPerfil().getLabel().toUpperCase()); 

        return Jwt.issuer("kauan-games-jwt")
                .subject(usuario.getEmail())
                .groups(roles)
                .claim("id", usuario.getId())
                .expiresAt(expiryDate)
                .sign();
    }
}
