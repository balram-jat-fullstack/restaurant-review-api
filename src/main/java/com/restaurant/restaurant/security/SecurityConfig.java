package com.restaurant.restaurant.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
        )
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/api/admin/**").hasRole("ADMIN")
//                .requestMatchers("/api/user/**").hasRole("USER")
//                .requestMatchers("/api/restaurants/**").hasAnyRole("USER", "ADMIN")
//                .anyRequest().authenticated()
//            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
