package me.devyonghee.order.service

import me.devyonghee.order.controller.OrderController.OrderRequest
import me.devyonghee.order.controller.OrderController.OrderResponse
import me.devyonghee.order.domain.OrderRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderService(
    private val orderRepository: OrderRepository
) {
    fun create(request: OrderRequest): OrderResponse {
        return OrderResponse(orderRepository.save(request.toDomain()))
    }

    fun orderByOrderId(orderId: UUID): OrderResponse? {
        return orderRepository.findByOrderId(orderId)?.let { OrderResponse(it) }
    }

    fun ordersByUserId(userId: String): List<OrderResponse> {
        return orderRepository.findAllByUserId(userId).map { OrderResponse(it) }
    }
}
