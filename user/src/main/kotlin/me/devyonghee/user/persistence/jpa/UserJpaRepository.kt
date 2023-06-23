package me.devyonghee.user.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

internal interface UserJpaRepository : JpaRepository<UserEntity, Long> {

    fun findByUserId(userId: UUID): UserEntity?
    fun findByEmail(email: String): UserEntity?
}
