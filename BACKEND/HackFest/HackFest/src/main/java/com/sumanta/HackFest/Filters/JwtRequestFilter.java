package com.sumanta.HackFest.Filters;

import com.sumanta.HackFest.Entities.Role;
import com.sumanta.HackFest.Utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain) throws IOException, ServletException {
        final String header = request.getHeader("Authorization");
        String JwtToken = null;
        if (header != null && header.startsWith("Bearer ")) {
            JwtToken = header.substring(7);
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("jwt")) {
                        JwtToken = cookie.getValue();
                        System.out.println(JwtToken);
                    }
                }
            }
        }
        if (JwtToken != null && jwtTokenUtil.validateToken(JwtToken)) {
            String UserID = jwtTokenUtil.getIdFromToken(JwtToken);
            Role role = jwtTokenUtil.getRoleFromToken(JwtToken);
            Collection<SimpleGrantedAuthority> authorities = Collections.singleton(
                    new SimpleGrantedAuthority("ROLE_" + role.name())
            );
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    UserID,
                    null,
                    authorities
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
//        else {
//            response.sendError(HttpServletResponse.SC_CONFLICT, "Invalid JWT");
//            return;
//        }
        filterchain.doFilter(request, response);

    }
}