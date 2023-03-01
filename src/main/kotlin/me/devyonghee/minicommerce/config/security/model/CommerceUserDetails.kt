package me.devyonghee.minicommerce.config.security.model

import me.devyonghee.minicommerce.auth.domain.EmailPasswordAccount
import me.devyonghee.minicommerce.member.domain.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CommerceUserDetails(
    private val emailPasswordAccount: EmailPasswordAccount,
    private val member: Member
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return member.roles.map { RoleAuthority.valueOf(it) }
    }

    override fun getUsername(): String {
        return emailPasswordAccount.email.toString()
    }

    override fun getPassword(): String {
        return emailPasswordAccount.password
    }

    override fun isAccountNonExpired(): Boolean = false

    override fun isAccountNonLocked(): Boolean = false

    override fun isCredentialsNonExpired(): Boolean = false

    override fun isEnabled(): Boolean = false
}