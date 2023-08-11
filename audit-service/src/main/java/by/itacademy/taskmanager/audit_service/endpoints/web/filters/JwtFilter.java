package by.itacademy.taskmanager.audit_service.endpoints.web.filters;

import by.itacademy.taskmanager.audit_service.endpoints.web.utils.JwtTokenHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@AllArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtTokenHandler jwtHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        if (!jwtHandler.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        UserDetails userDetails = userDetailsService
                .loadUserByUsername(jwtHandler.getUsername(token));

        UsernamePasswordAuthenticationToken authToken;

        if (userDetails == null){
            authToken = new UsernamePasswordAuthenticationToken(null,
                    null,
                    List.of());
        } else {
            if (!userDetails.isAccountNonLocked()){
                chain.doFilter(request, response);
                return;
            }

            authToken = new UsernamePasswordAuthenticationToken(userDetails,
                    null,
                    userDetails.getAuthorities());
        }

        authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);
        chain.doFilter(request, response);
    }
}
