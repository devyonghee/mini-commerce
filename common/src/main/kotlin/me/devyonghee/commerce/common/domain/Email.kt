package me.devyonghee.commerce.common.domain

data class Email(
    private val email: String
) {
    init {
        require(email.isNotBlank()) { "email must not be blank" }
        require(email.matches(EMAIL_FORMAT)) { "email($email) must be in the format of email format" }
    }

    override fun toString(): String {
        return email
    }

    companion object {
        private val EMAIL_FORMAT = Regex("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")
    }
}