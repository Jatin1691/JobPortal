package com.jobportal.Security;

import com.jobportal.JWT.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistration;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

 @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((auth)->
//            auth.requestMatchers("/**").permitAll().anyRequest().authenticated()
//        );

        http.csrf(csrf->csrf.disable());
        http.authorizeHttpRequests(https->{
            https.requestMatchers("/auth/login","/users/register","/users/sendOtp/**","/users/verifyOtp/**","profile/**","jobs/**").permitAll()
                    .anyRequest().authenticated();

        });

        http.exceptionHandling(exception->{
            exception.authenticationEntryPoint(authenticationEntryPoint);
        });
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement(https->https.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();

    }
    @Bean
    public org.springframework.web.servlet.config.annotation.CorsRegistry corsConfiguration() {
        return new org.springframework.web.servlet.config.annotation.CorsRegistry() {
            @Override
            public CorsRegistration addMapping(String pattern) {
                super.addMapping(pattern)
                        .allowedOrigins("http://localhost:3000") // Allow frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Allowed HTTP methods
                return null;
            }
        };
    }
    }
