package me.devyonghee.member.persistence.jpa

import jakarta.persistence.*
import me.devyonghee.minicommerce.common.domain.Email
import me.devyonghee.minicommerce.member.domain.Address
import me.devyonghee.minicommerce.member.domain.PhoneNumber
import me.devyonghee.minicommerce.member.domain.Role

@Entity
class MemberEntity(
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