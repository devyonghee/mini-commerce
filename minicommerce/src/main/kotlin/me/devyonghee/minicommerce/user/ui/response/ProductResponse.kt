package me.devyonghee.minicommerce.user.ui.response

import me.devyonghee.minicommerce.product.domain.Product

data class ProductResponse(
    val id: Long,
    val name: String,
    val price: Long,
) {
    constructor(product: Product) : this(
        id = product.id!!,
        name = product.name,
        price = product.price.amount
    )
}
