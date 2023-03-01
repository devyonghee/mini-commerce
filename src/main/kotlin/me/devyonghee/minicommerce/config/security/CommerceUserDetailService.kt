package me.devyonghee.minicommerce.config.security

import me.devyonghee.minicommerce.auth.application.EmailPasswordAccountService
import me.devyonghee.minicommerce.auth.domain.EmailPasswordAccount
import me.devyonghee.minicommerce.common.domain.Email
import me.devyonghee.minicommerce.common.exception.NotFoundException
import me.devyonghee.minicommerce.config.security.model.CommerceUserDetails
import me.devyonghee.minicommerce.member.application.MemberService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CommerceUserDetailService(
    private val accountService: EmailPasswordAccountService,
    private val memberService: MemberService
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val account = account(email)
        return CommerceUserDetails(account, memberService.member(account.memberId))
    }

    private fun account(email: String): EmailPasswordAccount {
        try {
            return accountService.account(Email(email))
        } catch (e: NotFoundException) {
            throw UsernameNotFoundException("account is not exists")
        }
    }
}