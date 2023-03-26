package me.devyonghee.commerce.auth.persistence.jpa

import jakarta.persistence.*
import me.devyonghee.commerce.auth.model.Account
import me.devyonghee.commerce.auth.model.Role
import me.devyonghee.commerce.common.domain.Email
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity(name = "account")
@EntityListeners(value = [AuditingEntityListener::class])
internal class AccountEntity(
    private val email: String,
    private val password: String,
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    @ElementCollection(targetClass = Role::class)
    @CollectionTable(name = "member_role", joinColumns = [JoinColumn(name = "member_id")])
    var roles: List<Role> = listOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0,
) {
    constructor(account: Account) : this(
        account.email.toString(),
        account.password,
        account.roles,
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
            roles,
            id,
            createdAt,
            updatedAt
        )
    }
}
