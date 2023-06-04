package me.devyonghee.order.persistence

import java.util.UUID
import me.devyonghee.order.domain.Order
import me.devyonghee.order.domain.OrderRepository
import me.devyonghee.order.persistence.jpa.OrderEntity
import me.devyonghee.order.persistence.jpa.OrderJpaRepository
import org.springframework.stereotype.Repository

@Repository
internal class OrderDao(
    private val jpaRepository: OrderJpaRepository
) : OrderRepository {

    override fun save(order: Order): Order {
        return jpaRepository.save(OrderEntity(order)).toDomain()
    }

    override fun findByOrderId(orderId: UUID): Order? {
        return jpaRepository.findByOrderId(orderId)?.toDomain()
    }

    override fun findAllByUserId(userId: String): Collection<Order> {
        return jpaRepository.findAllByUserId(userId).map { it.toDomain() }
    }
}