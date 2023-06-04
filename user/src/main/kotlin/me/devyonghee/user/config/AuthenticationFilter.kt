package me.devyonghee.user.config

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class AuthenticationFilter(
    private val objectMapper: ObjectMapper
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val credential: LoginRequest = objectMapper.readValue(request.inputStream, LoginRequest::class.java)

        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                credential.email,
                credential.password
            )
        )
    }

    override fun successfulAuthentication(
        request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain, authResult: Authentication
    ) {

    }

    data class LoginRequest(
        val email: String,
        val password: String,
    )
}