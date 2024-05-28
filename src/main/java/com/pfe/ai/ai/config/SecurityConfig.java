package com.pfe.ai.ai.config;

import com.pfe.ai.ai.auth.CustomBasicAuthenticationEntryPoint;
import com.pfe.ai.ai.auth.CustomBearerTokenAuthenticationEntryPoint;
import com.pfe.ai.ai.system.exceptions.CustomBearerTokenAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CustomBasicAuthenticationEntryPoint basicAuthenticationEntryPoint;
    private final CustomBearerTokenAuthenticationEntryPoint bearerTokenAuthenticationEntryPoint;
    private final CustomBearerTokenAccessDeniedHandler bearerTokenAccessDeniedHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(c-> c.disable())
                .authorizeHttpRequests(req -> {
                    req.anyRequest().permitAll();
                })
                .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(basicAuthenticationEntryPoint))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .exceptionHandling(ex -> {
                    ex.authenticationEntryPoint(bearerTokenAuthenticationEntryPoint);
                    ex.accessDeniedHandler(bearerTokenAccessDeniedHandler);
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
