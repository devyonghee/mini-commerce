package me.devyonghee.minicommerce.member.domain

import jakarta.persistence.*

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {
    init {
        require(name.isNotBlank()) { "name must not be blank" }
    }
}