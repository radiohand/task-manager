package by.itacademy.taskmanager.audit_service.endpoints.web.utils;

import by.itacademy.taskmanager.audit_service.config.property.AppProperty;
import by.itacademy.taskmanager.audit_service.core.exceptions.custom.BadTokenException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenHandler {
    private final AppProperty property;

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
