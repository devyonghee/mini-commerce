package com.yongpang.product.domain

import com.yongpang.common.domain.AbstractTimestampEntity
import javax.persistence.*

@Entity
class Product(
    name: ProductName,
    price: ProductPrice
) : AbstractTimestampEntity() {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        private set

    @Embedded
    var name = name
        private set

    @Embedded
    var price = price
        private set

    fun updateName(name: ProductName) {
        this.name = name
    }

    fun updatePrice(price: ProductPrice) {
        this.price = price
    }
}
