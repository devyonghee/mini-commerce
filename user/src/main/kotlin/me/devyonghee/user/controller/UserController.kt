package me.devyonghee.user.controller

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import me.devyonghee.user.domain.User
import me.devyonghee.user.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.*

@RestController
class UserController(
        private val userService: UserService
) {

    @PostMapping("/users")
    fun create(@RequestBody request: UserRequest): UserResponse {
        return userService.create(request)
    }

    data class UserRequest(
            @Email
            @NotBlank(message = "email can not be blank")
            @Size(min = 2, message = "email length must be more than 2")
            val email: String,

            @NotBlank(message = "name can not be blank")
            @Size(min = 2, message = "name length must be more than 2")
            val name: String,

            @Size(min = 8)
            @NotBlank(message = "password can not be blank")
            val password: String
    )

    data class UserResponse(
            val id: Long,
            val userId: UUID,
            val email: String,
            val name: String,
            val createdAt: LocalDateTime
    ) {
        constructor(user: User) : this(
                id = user.id,
                userId = user.userId,
                email = user.email,
                name = user.name,
                createdAt = user.createdAt
        )
    }
}