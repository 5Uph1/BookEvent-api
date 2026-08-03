package id.alif.services;

import id.alif.entity.User;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@ApplicationScoped
public class JwtService {

    public String generateToken(User user) {
        return Jwt.issuer("event-booking-api")
                .upn(user.email)
                .subject(String.valueOf(user.id))
                .claim("name", user.name)
                .groups(user.role.name())
                .expiresIn(Duration.ofHours(24))
                .sign();
    }
}