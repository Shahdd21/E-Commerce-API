package com.project.e_commerce_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/products").hasRole("VENDOR")
                        .requestMatchers(HttpMethod.PUT,"/products/**").hasRole("VENDOR")
                        .requestMatchers(HttpMethod.PATCH,"/products/**").hasRole("VENDOR")
                        .requestMatchers(HttpMethod.DELETE,"/products/**").hasAnyRole("VENDOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orders").hasAnyRole("CUSTOMER", "VENDOR")
                        .requestMatchers(HttpMethod.GET, "/orders/{orderId:\\\\d+}").hasAnyRole("CUSTOMER","ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/orders/{orderId:\\\\d+}/cancel").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/orders/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/orders/{orderId:\\\\d+}/status").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/payments/**").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/payments/**").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/reviews/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/reviews/{productId:\\\\d+}").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.PATCH, "/reviews/{reviewId:\\\\d+}").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE, "/reviews/{reviewId:\\\\d+}").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/wishlist").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/wishlist/{productId:\\\\d+}").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE, "/wishlist/{productId:\\\\d+}").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/categories").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vendors").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}


















