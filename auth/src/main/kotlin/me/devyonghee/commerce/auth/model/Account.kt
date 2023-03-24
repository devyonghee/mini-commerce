package me.devyonghee.commerce.auth.model

import me.devyonghee.commerce.common.domain.Email
import java.time.LocalDateTime

class Account(
    val email: Email,
    val password: String,
    var roles: List<Role>,
    val id: Long = 0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    init {
        require(password.isNotBlank()) { "password must not be blank" }
        require(roles.isNotEmpty()) { "roles must not be empty" }
    }

}