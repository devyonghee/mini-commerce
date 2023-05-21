package me.devyonghee.user.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun httpSecurity(http: HttpSecurity): SecurityFilterChain {
        return http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin().disable()
                .authorizeHttpRequests()
                .requestMatchers("/users/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}