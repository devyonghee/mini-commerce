package com.yongpang.product.domain

import com.yongpang.common.domain.AbstractTimestampEntity
import javax.persistence.*

@Entity
class Product(
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,

    @Embedded
    private val name: ProductName,

    @Embedded
    private val price: ProductPrice,
) : AbstractTimestampEntity() {
}
