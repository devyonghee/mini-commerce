package me.devyonghee.member.domain

import jakarta.persistence.*
import me.devyonghee.common.domain.Email

@Entity
class Member(
    @Column
    var name: String,
    @Embedded
    var email: Email,
    @Embedded
    var address: Address,
    @Embedded
    var phoneNumber: PhoneNumber,

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    @ElementCollection(targetClass = Role::class)
    @CollectionTable(name = "member_role", joinColumns = [JoinColumn(name = "member_id")])
    var role: List<Role> = listOf(Role.CUSTOMER),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) {
    init {
        require(name.isNotBlank()) { "name must not be blank" }
    }
}