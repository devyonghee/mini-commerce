package me.devyonghee.commerce.product.domain

import me.devyonghee.commerce.common.domain.Money
import java.time.LocalDateTime

data class Product(
    val name: String,
    var price: Money,
    var id: Long = 0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val createdBy: String = "",
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    var updatedBy: String = ""
) {

    init {
        require(name.isNotBlank()) { "Product name must not be blank" }
    }
}