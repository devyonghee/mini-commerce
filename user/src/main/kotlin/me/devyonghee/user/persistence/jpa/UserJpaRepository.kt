package me.devyonghee.user.persistence.jpa

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

internal interface UserJpaRepository : JpaRepository<UserEntity, Long> {

    fun findByUserId(userId: UUID): UserEntity?
    fun findByEmail(email: String): UserEntity?
}