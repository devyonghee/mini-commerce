package me.devyonghee.minicommerce.member.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Email(
    @Column
    private val email: String
) {
    init {
        require(email.isNotBlank()) { "email must not be blank" }
        require(email.matches(EMAIL_FORMAT)) { "email($email) must be in the format of email format" }

    }

    companion object {
        private val EMAIL_FORMAT = Regex("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")
    }
}