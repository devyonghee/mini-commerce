package me.devyonghee.minicommerce.member.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Address(
    @Column
    private val address: String,
    @Column
    private val addressDetail: String,
    @Column
    private val postCode: String,
) {
    init {
        require(address.isNotBlank()) { "address must not be blank" }
        require(addressDetail.isNotBlank()) { "addressDetail must not be blank" }
        require(postCode.isNotBlank()) { "postCode must not be blank" }
    }
}