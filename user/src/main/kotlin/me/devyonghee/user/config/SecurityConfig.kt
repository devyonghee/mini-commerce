package me.devyonghee.user.config

import com.fasterxml.jackson.databind.ObjectMapper
import me.devyonghee.user.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val userDetailService: UserDetailsService,
    private val userService: UserService,
    private val jwtProperty: JwtProperty
) {

    @Bean
    fun httpSecurity(
        http: HttpSecurity,
        authenticationManagerBuilder: AuthenticationManagerBuilder
    ): SecurityFilterChain {
        return http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .formLogin().disable()
            .userDetailsService(userDetailService)
            .apply(AuthenticationConfigurer(objectMapper, userService, jwtProperty))
            .and()
            .build()
    }
}
