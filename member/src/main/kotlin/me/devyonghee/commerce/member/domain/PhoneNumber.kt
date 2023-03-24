package me.devyonghee.commerce.member.domain

class PhoneNumber(
    private val number: String
) {
    init {
        require(number.matches(NUMBER_FORMAT)) { "phone number($number) must be in the format of $NUMBER_FORMAT" }
    }

    companion object {
        private val NUMBER_FORMAT = Regex("^[0-9]{10,11}$")
    }
}