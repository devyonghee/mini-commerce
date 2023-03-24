package me.devyonghee.commerce.application.config.security

import me.devyonghee.commerce.auth.model.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
class SecurityWebConfig {

    @Bean
    fun filterChain(
        http: HttpSecurity,
        successHandler: AuthenticationSuccessHandler,
        failureHandler: AuthenticationFailureHandler
    ): SecurityFilterChain {
        return http.csrf().disable()
            .formLogin()
            .loginProcessingUrl("/v1/login")
            .successHandler(successHandler)
            .failureHandler(failureHandler)
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/customer/v1/**").permitAll()
            .requestMatchers("/admin/v1/**").hasRole(Role.ADMIN.name)
            .and()
            .build()
    }
}