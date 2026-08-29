package com.portafolio.dt.config;
import com.portafolio.dt.security.JwtFilter; 
import org.springframework.context.annotation.*; 
import org.springframework.security.authentication.*; 
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity; 
import org.springframework.security.config.http.SessionCreationPolicy; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.security.web.*; 
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.List;

@Configuration 
public class SecurityConfig { 
    @Bean PasswordEncoder 
    passwordEncoder(){
        return new BCryptPasswordEncoder();
    } 
    @Bean AuthenticationManager 
    authenticationManager(AuthenticationConfiguration c)throws Exception{
        return c.getAuthenticationManager();
    } 
    @Bean SecurityFilterChain filterChain(HttpSecurity h,JwtFilter j)throws Exception{
        h
        .csrf(c->c.disable())
        .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(a->a.requestMatchers("/api/auth/**","/swagger-ui/**","/swagger-ui.html","/api-docs/**")
        .permitAll()
        .anyRequest()
        .authenticated())
        .addFilterBefore(j,UsernamePasswordAuthenticationFilter.class);
        return h.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(
            "https://portafolio-design-thinking-frontend.onrender.com"
        ));

        configuration.setAllowedMethods(List.of(
            "GET",
            "POST",
            "PUT",
            "DELETE",
            "OPTIONS"
        ));

        configuration.setAllowedHeaders(List.of("*"));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
