package by.itacademy.taskmanager.userservice.endpoints.web.utils;

import by.itacademy.taskmanager.userservice.config.property.AppProperty;
import by.itacademy.taskmanager.userservice.core.exceptions.custom.BadTokenException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtTokenHandler {
    private final AppProperty property;

    public String generateAccessToken(UserDetails user) {
        return generateAccessToken(user.getUsername());
    }

    public String generateAccessToken(String name) {
        return Jwts.builder()
                .setSubject(name)
                .setIssuer(property.getJwtProp().getIssuer())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30))) // 1 month
                .signWith(SignatureAlgorithm.HS512, property.getJwtProp().getSecret())
                .compact();
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getJwtProp().getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getJwtProp().getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(property.getJwtProp().getSecret()).parseClaimsJws(token);
            return true;
        } catch (SignatureException | MalformedJwtException ex) {
            throw new BadTokenException("Invalid token");
        } catch (ExpiredJwtException ex) {
            throw new BadTokenException("Token is expired");
        } catch (UnsupportedJwtException | IllegalArgumentException ex) {
            throw new BadTokenException();
        }
    }
}
