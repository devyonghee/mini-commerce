package me.devyonghee.user.domain

interface UserRepository {

    fun save(user: User): User
}