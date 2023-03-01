package me.devyonghee.minicommerce.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(
        http: HttpSecurity,
        userDetailService: UserDetailsService,
    ): SecurityFilterChain {
        return http.csrf().disable()
            .cors().disable()
            .userDetailsService(userDetailService)
            .authorizeHttpRequests()
            .requestMatchers("/v1/products/**").permitAll()
            .anyRequest().hasRole("customer")
            .and()
            .build()
    }

}
