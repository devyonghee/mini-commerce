package com.yongpang.product.ui.response

import com.yongpang.product.domain.Product
import java.math.BigDecimal

data class ProductResponse(
    val id: Long,
    val name: String,
    val price: BigDecimal
) {
    constructor(product: Product) : this(
        product.id!!,
        product.name.name,
        product.price.price
    )
}
