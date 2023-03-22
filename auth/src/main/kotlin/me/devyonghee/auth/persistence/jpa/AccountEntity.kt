package me.devyonghee.auth.persistence.jpa

import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import me.devyonghee.auth.model.Account
import me.devyonghee.auth.model.Role
import me.devyonghee.common.domain.Email
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

class AccountEntity(
    private val email: String,
    private val password: String,
    @Enumerated(EnumType.STRING)
    private val role: Role,
    private val id: Long = 0,
) {
    constructor(account: Account) : this(
        account.email.toString(),
        account.password,
        account.role,
    )

    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @CreatedBy
    lateinit var createdBy: String

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime

    @LastModifiedBy
    lateinit var updatedBy: String

    fun toDomain(): Account {
        return Account(
            Email(email),
            password,
            role,
            id,
            createdAt,
            updatedAt
        )
    }
}
