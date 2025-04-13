package com.sumanta.HackFest.Filters;

import com.sumanta.HackFest.Entities.Role;
import com.sumanta.HackFest.Utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain) throws IOException {
        final String header = request.getHeader("Authorization");
        if(header!=null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if(jwtTokenUtil.validateToken(token)) {
                String userId = jwtTokenUtil.getIdFromToken(token);
                Role role = jwtTokenUtil.getRoleFromToken(token);
                Collection<SimpleGrantedAuthority> authorities = Collections.singleton(
                        new SimpleGrantedAuthority("ROLE_"+role.name())
                );
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userId,
                        null,
                        authorities
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                response.sendError(HttpServletResponse.SC_CONFLICT, "Invalid JWT");
                return;
            }
        }
    }
}
