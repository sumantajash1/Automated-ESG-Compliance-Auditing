package com.sumanta.HackFest.Configurations;

import com.sumanta.HackFest.Filters.JwtRequestFilter;
import com.sumanta.HackFest.Utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtRequestFilter filter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterchain (HttpSecurity security) throws Exception {
        security
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/Client/SignUp",
                                                    "/Client/Login",
                                                    "/Supplier/SignUp",
                                                    "/Supplier/Login",
                                                    "/Government/Login",
                                                    "/Government/SignUp"
                        ).permitAll()
                        .requestMatchers("/Client/**").hasRole("CLIENT")
                        .requestMatchers("/Government/**").hasRole("GOVERNMENT")
                        .requestMatchers("/Supplier/**").hasRole("SUPPLIER")
                        .anyRequest().authenticated())
                        .sessionManagement(session ->
                            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return security.build();
    }
}
