package me.devyonghee.order.persistence.jpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import me.devyonghee.order.domain.Order
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "orders")
class OrderEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,
    @Column(nullable = false, length = 120, unique = true)
    private val productId: String,
    @Column(nullable = false)
    private val quantity: Int,
    @Column(nullable = false)
    private val unitPrice: Int,
    @Column(nullable = false)
    private val totalPrice: Int,

    @Column(nullable = false)
    private val userId: String,
    @Column(nullable = false, unique = true)
    private val orderId: UUID,

    @Column(nullable = false, updatable = false)
    private val createdAt: LocalDateTime
) {
    constructor(order: Order) : this(
        id = order.id,
        productId = order.productId,
        quantity = order.quantity,
        unitPrice = order.unitPrice,
        totalPrice = order.totalPrice,
        userId = order.userId,
        orderId = order.orderId,
        createdAt = order.createdAt
    )

    fun toDomain(): Order {
        return Order(
            id = id,
            productId = productId,
            quantity = quantity,
            unitPrice = unitPrice,
            totalPrice = totalPrice,
            userId = userId,
            orderId = orderId,
            createdAt = createdAt
        )
    }
}
