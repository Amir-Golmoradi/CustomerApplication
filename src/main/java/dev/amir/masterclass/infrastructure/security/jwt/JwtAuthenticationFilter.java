package dev.amir.masterclass.infrastructure.security.jwt;

import dev.amir.masterclass.infrastructure.security.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        /* Authentication Header Key */

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String jwtToken = authHeader.substring(7);
            if (!jwtToken.isEmpty()) {

                /* Extract the subject from Jwt Token */
                String subject = jwtUtils.getSubjectFromToken(jwtToken);

                /* Load User Details */
               /* SecurityContextHolder.getContext().getAuthentication() == null
                   -> If User is not authenticated
                  If there is no user with this details.
                 */
                if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(subject);

                    /* Validate Token ->  ثبت توکن کاربر */
                    if (jwtUtils.isTokenValid(jwtToken, userDetails.getUsername())) {
                        /* توکن ثبت یوزر و پسوورد کاربر */
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                        /*  ثبت نهایی اطلاعات و توکن کاربر*/
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            } else {
                filterChain.doFilter(request, response);
            }

        }
    }
}
