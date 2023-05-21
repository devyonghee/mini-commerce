package me.devyonghee.user.service

import me.devyonghee.user.controller.UserController.UserRequest
import me.devyonghee.user.controller.UserController.UserResponse
import me.devyonghee.user.domain.User
import me.devyonghee.user.domain.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
        private val passwordEncoder: PasswordEncoder,
        private val userRepository: UserRepository
) {

    @Transactional
    fun create(request: UserRequest): UserResponse {
        return User(
                email = request.email,
                name = request.name,
                password = passwordEncoder.encode(request.password)
        ).let { UserResponse(userRepository.save(it)) }
    }
}