package me.devyonghee.user.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.util.Date
import me.devyonghee.user.domain.User
import me.devyonghee.user.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class AuthenticationFilter(
    private val objectMapper: ObjectMapper,
    private val userService: UserService,
    private val jwtProperty: JwtProperty,
    authenticationManager: AuthenticationManager,
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val credential: LoginRequest = objectMapper.readValue(request.inputStream, LoginRequest::class.java)

        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken.unauthenticated(
                credential.email,
                credential.password
            )
        )
    }

    override fun successfulAuthentication(
        request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain, authResult: Authentication,
    ) {
        val user: User =
            userService.findByEmail(authResult.name) ?: throw IllegalArgumentException("username is not exist")

        val token: String = Jwts.builder()
            .setSubject(user.userId.toString())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperty.expirationTime))
            .signWith(Keys.hmacShaKeyFor(jwtProperty.secret.toByteArray()), SignatureAlgorithm.HS512)
            .compact()

        response.addHeader("token", token)
        response.addHeader("userId", user.userId.toString())
    }

    data class LoginRequest(
        val email: String,
        val password: String,
    )
}