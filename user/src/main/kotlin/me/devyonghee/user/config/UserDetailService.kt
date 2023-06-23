package me.devyonghee.user.config

import me.devyonghee.user.service.UserService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailService(
    private val userService: UserService
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user: me.devyonghee.user.domain.User = (
            userService.findByEmail(username)
                ?: throw UsernameNotFoundException("username password is not matched")
            )

        return User(user.email, user.password, emptyList())
    }
}
