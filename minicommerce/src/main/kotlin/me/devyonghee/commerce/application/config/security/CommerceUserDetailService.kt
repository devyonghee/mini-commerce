package me.devyonghee.commerce.application.config.security

import me.devyonghee.commerce.auth.application.AccountService
import me.devyonghee.commerce.common.domain.Email
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component


@Component
class CommerceUserDetailService(
    private val accountService: AccountService
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val email = Email(username)
        if (isNotExists(email)) {
            throw BadCredentialsException("bad credentials")
        }
        return AccountUserDetails(accountService.account(email))
    }

    private fun isNotExists(email: Email) = !accountService.isExists(email)
}