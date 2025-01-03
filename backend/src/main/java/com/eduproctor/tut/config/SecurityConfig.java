package com.eduproctor.tut.config;

import com.eduproctor.tut.filter.JwtAuthenticationFilter;
import com.eduproctor.tut.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                // Explicitly configure CSRF protection (you can customize this as needed)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/auth/**","api/clients/**", "/h2-console/**","/api/categories/**","/api/**")  // Disable CSRF for authentication endpoints if needed
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/**","api/clients/**", "/h2-console/**","/api/categories/**","/api/**").permitAll()  // Allow access to /api/auth/** without authentication
                        .anyRequest().authenticated()  // Authenticate other requests
                )
                .headers(headers -> headers
                        .xssProtection(withDefaults()) // Enable XSS protection
                        .frameOptions(frameOptions -> frameOptions.disable()) // Disable X-Frame-Options header for H2 console
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(withDefaults())  // Default form login configuration
                .logout(withDefaults());    // Default logout configuration

        return http.build();
    }

//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() {
//        return new JwtAuthenticationFilter(jwtUtils);
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailsService)  // Provide user details service
//                .passwordEncoder(passwordEncoder())    // Provide password encoder
//                .build();
//    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)  // Provide user details service
                .passwordEncoder(passwordEncoder());    // Provide password encoder
        return authenticationManagerBuilder.build();
    }


}

