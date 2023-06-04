package me.devyonghee.order.domain

import java.time.LocalDateTime
import java.util.UUID

data class Order(
    val productId: String,
    val quantity: Int,
    val unitPrice: Int,
    val userId: String,
    val totalPrice: Int = quantity * unitPrice,
    val orderId: UUID = UUID.randomUUID(),
    val id: Long = 0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
}
