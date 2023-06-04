package me.devyonghee.user.service

import java.util.UUID
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
    private val repository: UserRepository
) {

    @Transactional
    fun create(request: UserRequest): UserResponse {
        return User(
            email = request.email,
            name = request.name,
            password = passwordEncoder.encode(request.password)
        ).let { UserResponse(repository.save(it)) }
    }

    fun user(userId: UUID): UserResponse {
        return repository.findByUserId(userId)
            ?.let { return UserResponse(it) }
            ?: throw IllegalArgumentException("user is not exist. user(userId: `$userId`))")
    }

    fun users(): Collection<UserResponse> {
        return repository.findAll().map { UserResponse(it) }
    }

    fun findByEmail(email: String): User? {
        return repository.findByEmail(email)
    }
}