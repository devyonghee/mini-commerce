package com.yongpang.product.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ProductName(
    @Column(nullable = false, length = 100)
    val name: String
) {
    init {
        require(name.isNotBlank()) { "product name must not be blank" }
    }
}
