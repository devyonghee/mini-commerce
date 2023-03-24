package me.devyonghee.commerce.member.domain

class Address(
    val address: String,
    val addressDetail: String,
    val postCode: String,
) {
    init {
        require(address.isNotBlank()) { "address must not be blank" }
        require(postCode.isNotBlank()) { "postCode must not be blank" }
    }
}