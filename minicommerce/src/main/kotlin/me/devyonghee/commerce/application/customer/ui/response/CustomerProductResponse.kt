package me.devyonghee.commerce.application.customer.ui.response

import me.devyonghee.commerce.product.domain.Product

data class CustomerProductResponse(
    val id: Long,
    val name: String,
    val price: Long,
) {
    constructor(product: Product) : this(
        id = product.id,
        name = product.name,
        price = product.price.amount
    )
}
