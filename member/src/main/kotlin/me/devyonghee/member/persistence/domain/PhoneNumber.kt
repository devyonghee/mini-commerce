package me.devyonghee.minicommerce.member.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class PhoneNumber(
    @Column(name = "phone_number", length = 11, nullable = false)
    private val number: String
) {
    init {
        require(number.matches(NUMBER_FORMAT)) { "phone number($number) must be in the format of $NUMBER_FORMAT" }
    }

    companion object {
        private val NUMBER_FORMAT = Regex("^[0-9]{10,11}$")
    }
}