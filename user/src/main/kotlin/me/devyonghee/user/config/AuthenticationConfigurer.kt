package me.devyonghee.user.config

import com.fasterxml.jackson.databind.ObjectMapper
import me.devyonghee.user.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer

class AuthenticationConfigurer(
    private val objectMapper: ObjectMapper,
    private val userService: UserService,
    private val jwtProperty: JwtProperty
) : AbstractHttpConfigurer<AuthenticationConfigurer, HttpSecurity>() {

    override fun configure(http: HttpSecurity) {
        http.addFilter(
            AuthenticationFilter(
                objectMapper,
                userService,
                jwtProperty,
                http.getSharedObject(AuthenticationManager::class.java)
            )
        )
    }
}
