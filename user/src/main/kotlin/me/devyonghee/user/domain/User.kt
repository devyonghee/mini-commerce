package me.devyonghee.user.domain

import java.time.LocalDateTime
import java.util.*

data class User(
    val email: String,
    val name: String,
    val password: String,
    val id: Long = 0,
    val userId: UUID = UUID.randomUUID(),
    val createdAt: LocalDateTime = LocalDateTime.now()
)
