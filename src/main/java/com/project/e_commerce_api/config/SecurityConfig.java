package com.project.e_commerce_api.config;

import com.project.e_commerce_api.auth.AuthenticationEntryPointImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationEntryPointImp authenticationEntryPoint;

    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider, AuthenticationEntryPointImp authenticationEntryPoint) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole("SUPER_ADMIN","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/products").hasRole("VENDOR")
                        .requestMatchers(HttpMethod.PUT,"/products/**").hasRole("VENDOR")
                        .requestMatchers(HttpMethod.PATCH,"/products/**").hasRole("VENDOR")
                        .requestMatchers(HttpMethod.DELETE,"/products/**").hasAnyRole("VENDOR", "ADMIN", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orders").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/orders/{orderId:\\\\d+}").hasAnyRole("CUSTOMER","ADMIN", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/orders/{orderId:\\\\d+}/cancel").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/orders/all").hasAnyRole("ADMIN", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/orders/{orderId:\\\\d+}/status").hasAnyRole("ADMIN","SUPER_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/payments/**").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/payments/**").hasAnyRole("CUSTOMER", "ADMIN", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/reviews/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/reviews/{productId:\\\\d+}").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.PATCH, "/reviews/{reviewId:\\\\d+}").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE, "/reviews/{reviewId:\\\\d+}").hasAnyRole("CUSTOMER", "ADMIN", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/wishlist").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/wishlist/{productId:\\\\d+}").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE, "/wishlist/{productId:\\\\d+}").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/categories").permitAll()
                        .requestMatchers(HttpMethod.POST, "/categories").hasAnyRole("ADMIN", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/categories/{categoryId:\\\\d+}").hasAnyRole("ADMIN", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/categories/{categoryId:\\\\d+}").hasAnyRole("ADMIN", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/vendors").permitAll()
                        .requestMatchers("/wallet").hasRole("CUSTOMER")

                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}


















