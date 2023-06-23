package me.devyonghee.user.persistence

import me.devyonghee.user.domain.User
import me.devyonghee.user.domain.UserRepository
import me.devyonghee.user.persistence.jpa.UserEntity
import me.devyonghee.user.persistence.jpa.UserJpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
internal class UserDao(
    private val jpaRepository: UserJpaRepository
) : UserRepository {

    override fun save(user: User): User {
        return jpaRepository.save(UserEntity(user)).toDomain()
    }

    override fun findByUserId(userId: UUID): User? {
        return jpaRepository.findByUserId(userId)?.toDomain()
    }

    override fun findAll(): Collection<User> {
        return jpaRepository.findAll().map { it.toDomain() }
    }

    override fun findByEmail(email: String): User? {
        return jpaRepository.findByEmail(email)?.toDomain()
    }
}
