package me.devyonghee.commerce.application.admin.ui.response

import me.devyonghee.commerce.product.domain.Product
import java.time.LocalDateTime

data class AdminProductResponse(
    val id: Long,
    val name: String,
    val price: Long,
    val createdAt: LocalDateTime,
    val createdBy: String,
    val updatedAt: LocalDateTime,
    val updatedBy: String
) {
    constructor(product: Product) : this(
        id = product.id,
        name = product.name,
        price = product.price.amount,
        createdAt = product.createdAt,
        createdBy = product.createdBy,
        updatedAt = product.updatedAt,
        updatedBy = product.updatedBy,
    )
}
