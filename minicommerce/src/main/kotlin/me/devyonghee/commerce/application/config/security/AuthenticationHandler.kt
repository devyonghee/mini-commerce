package me.devyonghee.commerce.application.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class AuthenticationHandler(
    private val objectMapper: ObjectMapper
) : AuthenticationSuccessHandler, AuthenticationFailureHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val username: String = (authentication.principal as UserDetails).username
        response.writer.write(objectMapper.writeValueAsString(username))
    }

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        response.apply {
            status = HttpStatus.UNAUTHORIZED.value()
            writer.write(
                objectMapper.writeValueAsString(
                    ProblemDetail.forStatusAndDetail(
                        HttpStatus.UNAUTHORIZED,
                        exception.toString()
                    )
                )
            )
        }
    }
}