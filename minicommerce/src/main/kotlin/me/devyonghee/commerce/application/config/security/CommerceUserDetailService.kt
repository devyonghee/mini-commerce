package me.devyonghee.commerce.application.config.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component


@Component
class CommerceUserDetailService : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        TODO()
    }
}