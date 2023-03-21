package me.devyonghee.product.persistence.jpa

import me.devyonghee.common.domain.Money
import me.devyonghee.product.domain.Product
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

class ProductEntity(
    var name: String,
    var price: Long,
    var id: Long = 0,
) {
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime

    constructor(product: Product) : this(
        product.name,
        product.price.amount
    )

    fun toDomain(): Product {
        return Product(
            name,
            Money(price),
            id,
            createdAt,
            updatedAt
        )
    }
}