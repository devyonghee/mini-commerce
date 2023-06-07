package me.devyonghee.user.controller

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.net.URI
import java.time.LocalDateTime
import java.util.*
import me.devyonghee.user.domain.User
import me.devyonghee.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("/users")
    fun create(@RequestBody request: UserRequest): ResponseEntity<UserResponse> {
        val response = userService.create(request)
        return ResponseEntity.created(URI("/users/${response.userId}")).body(response)
    }

    @GetMapping("/users/{userId}")
    fun user(@PathVariable userId: UUID): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.user(userId))
    }

    @GetMapping("/users")
    fun users(): ResponseEntity<Collection<UserResponse>> {
        return ResponseEntity.ok(userService.users())
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class UserResponse(
        val email: String,
        val userId: UUID,
        val name: String,
        val orders: Collection<OrderResponse>
    ) {
        constructor(user: User, orders: Collection<OrderResponse> = emptyList()) : this(
            userId = user.userId,
            email = user.email,
            name = user.name,
            orders = orders
        )
    }

    data class OrderResponse(
        val orderId: String,
        val productId: String,
        val quantity: Int,
        val unitPrice: Int,
        val totalPrice: Int,
        val createdAt: LocalDateTime,
    )
}