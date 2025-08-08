package com.devtracker.DevTracker.config;

import com.devtracker.DevTracker.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    
    private CustomUserDetailsService userDetailsService;
    @Autowired
    public void setCustomUserDetailsService(CustomUserDetailsService userDetailsService) {
    	this.userDetailsService =  userDetailsService;
    }

    
    private JwtAuthFilter jwtAuthFilter;
    @Autowired
    public void setJwtAuthFilter (JwtAuthFilter jwtAuthFilter) {
    	this.jwtAuthFilter =  jwtAuthFilter;
    }

    private PasswordConfig passwordConfig;
    @Autowired
    public void setPasswordConfig (PasswordConfig passwordConfig) {
    	this.passwordConfig =   passwordConfig;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordConfig.passwordEncoder())
                .and()
                .build();
    }


}
