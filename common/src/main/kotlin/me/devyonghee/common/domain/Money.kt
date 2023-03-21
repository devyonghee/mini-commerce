package me.devyonghee.common.domain

class Money(
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