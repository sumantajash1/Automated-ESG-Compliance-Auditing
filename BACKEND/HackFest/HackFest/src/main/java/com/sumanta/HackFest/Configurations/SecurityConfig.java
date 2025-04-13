package com.sumanta.HackFest.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterchain (HttpSecurity security) throws Exception {
        security
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/Business/SignUp",
                                                    "/Business/Login",
                                                    "/Supplier/SignUp",
                                                    "/Supplier/Login",
                                                    "/Government/Login"
                        ).permitAll()
                        .requestMatchers("/Business/**").hasRole("CLIENT")
                        .requestMatchers("/Government/**").hasRole("GOVERNMENT")
                        .requestMatchers("/Supplier/**").hasRole("SUPPLIER")
                        .anyRequest().authenticated()
                ).sessionManagement(session ->
                            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        );
        return security.build();
    }
}
