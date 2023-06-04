package me.devyonghee.order.controller

import com.fasterxml.jackson.annotation.JsonInclude
import java.net.URI
import java.time.LocalDateTime
import java.util.UUID
import me.devyonghee.order.domain.Order
import me.devyonghee.order.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order-service")
class OrderController(
    private val orderService: OrderService
) {

    @PostMapping("/orders")
    fun createOrder(@RequestBody request: OrderRequest): ResponseEntity<OrderResponse> {
        val response = orderService.create(request)
        return ResponseEntity.created(URI("/orders/${response.orderId}")).body(response)
    }

    @GetMapping("/orders")
    fun ordersByUserId(@RequestParam userId: String): ResponseEntity<List<OrderResponse>> {
        return ResponseEntity.ok(orderService.ordersByUserId(userId))
    }

    data class OrderRequest(
        val productId: String,
        val quantity: Int,
        val unitPrice: Int,
        val userId: String,
    ) {
        fun toDomain(): Order {
            return Order(
                productId = productId,
                quantity = quantity,
                unitPrice = unitPrice,
                userId = userId,
            )
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class OrderResponse(
        val id: Long,
        val productId: String,
        val quantity: Int,
        val unitPrice: Int,
        val totalPrice: Int,
        val userId: String,
        val orderId: UUID,
        val createdAt: LocalDateTime,
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
    }
}