package me.devyonghee.user.persistence.jpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import me.devyonghee.user.domain.User
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long,
    @Column(nullable = false, unique = true)
    private var email: String,
    @Column(nullable = false)
    private var name: String,
    @Column(nullable = false)
    private var password: String,
    @Column(nullable = false, unique = true)
    private var userId: UUID,
    @Column(nullable = false, updatable = false)
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
