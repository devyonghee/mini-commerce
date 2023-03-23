package me.devyonghee.member.domain

import me.devyonghee.common.domain.Email

class Member(
    val name: String,
    val email: Email,
    val address: Address,
    val phoneNumber: PhoneNumber,
    val accountId: Long,
    val id: Long = 0
) {
    init {
        require(name.isNotBlank()) { "name must not be blank" }
    }
}