package me.devyonghee.order.persistence.jpa

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

internal interface OrderJpaRepository : JpaRepository<OrderEntity, Long> {

    fun findByOrderId(orderId: UUID): OrderEntity?

    fun findAllByUserId(userId: String): Collection<OrderEntity>
}