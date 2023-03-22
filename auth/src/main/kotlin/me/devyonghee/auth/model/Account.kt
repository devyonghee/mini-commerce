package me.devyonghee.auth.model

import me.devyonghee.common.domain.Email
import java.time.LocalDateTime

class Account(
    val email: Email,
    val password: String,
    val role: Role,
    val id: Long = 0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {

}