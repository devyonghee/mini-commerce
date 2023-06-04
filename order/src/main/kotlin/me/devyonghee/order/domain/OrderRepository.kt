package me.devyonghee.order.domain

import java.util.UUID

interface OrderRepository {

    fun save(order: Order): Order

    fun findByOrderId(orderId: UUID): Order?

    fun findAllByUserId(userId: String): Collection<Order>
}
