package me.devyonghee.commerce.product.persistence.jpa

import jakarta.persistence.*
import me.devyonghee.commerce.common.domain.Money
import me.devyonghee.commerce.product.domain.Product
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity(name = "product")
@EntityListeners(value = [AuditingEntityListener::class])
internal class ProductEntity(
    var name: String,
    var price: Long,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    fun change(product: Product) {
        name = product.name
        price = product.price.amount
    }

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
