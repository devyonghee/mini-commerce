package me.devyonghee.product.domain

import me.devyonghee.common.domain.Money
import java.time.LocalDateTime

class Product(
    val name: String,
    var price: Money,
    var id: Long = 0,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {

    init {
        require(name.isNotBlank()) { "Product name must not be blank" }
    }
}