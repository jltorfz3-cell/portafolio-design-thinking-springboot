package com.portafolio.dt.config;

import com.portafolio.dt.security.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;
import java.util.*;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Value("${app.cors.origins}") String origins;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration c = new CorsConfiguration();
        c.setAllowedOrigins(Arrays.stream(origins.split(",")).map(String::trim).toList());
        c.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
        c.setAllowedHeaders(List.of("*"));
        c.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource s = new UrlBasedCorsConfigurationSource();
        s.registerCorsConfiguration("/**", c);
        return s;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity h, JwtFilter j) throws Exception {
        h.csrf(x -> x.disable())
         .cors(x -> {})
         .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         .authorizeHttpRequests(a -> a
             .requestMatchers("/api/auth/**","/swagger-ui/**","/swagger-ui.html","/api-docs/**").permitAll()
             .anyRequest().authenticated())
         .addFilterBefore(j, UsernamePasswordAuthenticationFilter.class);
        return h.build();
    }
}
