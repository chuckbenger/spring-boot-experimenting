package com.example.springbootexperimenting.config

import com.example.springbootexperimenting.jwt.JWTAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityFilterChainConfiguration(
    private val jwtAuthenticationFilter: JWTAuthenticationFilter,
    private val authenticationProvider: AuthenticationProvider
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.csrf {
            it.disable()
        }.authorizeHttpRequests {
            it.requestMatchers(HttpMethod.GET).permitAll()
            it.requestMatchers(HttpMethod.GET, "/api/tweets/**").permitAll()
            it.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
            it.requestMatchers(HttpMethod.POST, "/api/tweets/**").authenticated()
        }.sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }.authenticationProvider(authenticationProvider).addFilterBefore(
            jwtAuthenticationFilter,
            UsernamePasswordAuthenticationFilter::class.java
        ).build()
    }
}