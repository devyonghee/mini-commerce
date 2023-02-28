package me.devyonghee.minicommerce.common.domain

import jakarta.persistence.Access
import jakarta.persistence.AccessType
import jakarta.persistence.Embeddable

@Embeddable
class Money(
    @Access(AccessType.FIELD)
    val amount: Long
) {

    init {
        require(isZeroOrPositive()) {
            "amount(${amount}) must be greater than 0"
        }
    }

    private fun isZeroOrPositive(): Boolean = amount >= 0

    operator fun times(quantity: Int): Money {
        return Money(amount * quantity)
    }

    operator fun plus(money: Money): Money {
        return Money(amount + money.amount)
    }
}