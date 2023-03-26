package me.devyonghee.commerce.application.config.security

import me.devyonghee.commerce.auth.model.Account
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AccountUserDetails(
    private val account: Account
) : UserDetails {


    override fun getAuthorities(): Collection<GrantedAuthority> {
        return account.roles.map { SimpleGrantedAuthority("ROLE_${it.name}") }
    }

    override fun getPassword(): String {
        return account.password
    }

    override fun getUsername(): String {
        return account.email.toString()
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}