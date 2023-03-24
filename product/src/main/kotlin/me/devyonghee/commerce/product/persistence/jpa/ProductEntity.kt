package me.devyonghee.commerce.product.persistence.jpa

import me.devyonghee.commerce.common.domain.Money
import me.devyonghee.commerce.product.domain.Product
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

class ProductEntity(
    var name: String,
    var price: Long,
    var id: Long = 0,
) {
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @CreatedBy
    lateinit var createdBy: String

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime

    @LastModifiedBy
    lateinit var updatedBy: String

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
            createdBy,
            updatedAt,
            updatedBy,
        )
    }
}
