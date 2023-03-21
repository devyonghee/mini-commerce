package me.devyonghee.member.domain

import jakarta.persistence.Embeddable

@Embeddable
class Address(
    private val address: String,
    private val addressDetail: String,
    private val postCode: String,
) {
    init {
        require(address.isNotBlank()) { "address must not be blank" }
        require(postCode.isNotBlank()) { "postCode must not be blank" }
    }
}