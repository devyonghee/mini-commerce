package com.yongpang.product.domain

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ProductPrice(
    @Column(nullable = false)
    private val price: BigDecimal
) {
    init {
        require(price > BigDecimal.ZERO) { "상품 가격은 0보다 커야 합니다." }
    }
}
