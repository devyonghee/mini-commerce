package me.devyonghee.user.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer

class AuthenticationConfigurer(
    private val objectMapper: ObjectMapper
) : AbstractHttpConfigurer<AuthenticationConfigurer, HttpSecurity>() {

    override fun configure(http: HttpSecurity) {
        http.addFilter(AuthenticationFilter(objectMapper, http.getSharedObject(AuthenticationManager::class.java)))
    }
}