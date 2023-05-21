package me.devyonghee.user.persistence.jpa

import jakarta.persistence.*
import me.devyonghee.user.domain.User
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "users")
class UserEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private var id: Long,
        private var email: String,
        private var name: String,
        private var password: String,
        private var userId: UUID,
        private var createdAt: LocalDateTime
) {
    constructor(user: User) : this(
            id = user.id,
            email = user.email,
            name = user.name,
            password = user.password,
            userId = user.userId,
            createdAt = user.createdAt
    )

    fun toDomain(): User {
        return User(
                id = id,
                email = email,
                name = name,
                password = password,
                userId = userId,
                createdAt = createdAt
        )
    }
}