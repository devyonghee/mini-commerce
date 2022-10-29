package com.yongpang.product.domain

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class ProductPrice(
    @Column(nullable = false)
    val price: BigDecimal
) {
    init {
        require(price > BigDecimal.ZERO) { "product price(${price}) must not be negative" }
    }
}
