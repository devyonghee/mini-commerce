package me.devyonghee.order.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

internal interface OrderJpaRepository : JpaRepository<OrderEntity, Long> {

    fun findByOrderId(orderId: UUID): OrderEntity?

    fun findAllByUserId(userId: String): Collection<OrderEntity>
}
