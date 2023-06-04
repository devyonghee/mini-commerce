package me.devyonghee.user.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val userDetailService: UserDetailService
) {

    @Bean
    fun httpSecurity(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .formLogin().disable()
            .userDetailsService(userDetailService)
            .authorizeHttpRequests()
            .and()
            .addFilter(AuthenticationFilter(objectMapper))
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}