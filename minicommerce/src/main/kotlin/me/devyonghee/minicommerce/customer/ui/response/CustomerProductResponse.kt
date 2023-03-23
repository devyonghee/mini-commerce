package me.devyonghee.minicommerce.customer.ui.response

import me.devyonghee.product.domain.Product

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
