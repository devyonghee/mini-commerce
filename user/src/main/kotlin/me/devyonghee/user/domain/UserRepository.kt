package me.devyonghee.user.domain

import java.util.UUID

interface UserRepository {

    fun save(user: User): User
    fun findByUserId(userId: UUID): User?
    fun findAll(): Collection<User>
    fun findByEmail(email: String): User?
}
