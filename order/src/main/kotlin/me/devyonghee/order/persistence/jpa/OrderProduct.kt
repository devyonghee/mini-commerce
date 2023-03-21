package me.devyonghee.order.domain

import jakarta.persistence.*
import me.devyonghee.minicommerce.common.domain.CreatedAuditEntity
import me.devyonghee.minicommerce.common.domain.Money

@Entity
class OrderProduct(
    @AttributeOverride(name = "amount", column = Column(name = "price"))
    private val price: Money,
    private val quantity: Int,
    private val productId: Long,
    @ManyToOne(optional = false)
    private val order: Order,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null
) : CreatedAuditEntity() {
    init {
        require(quantity > 0) { "quantity($quantity) must be greater than 0" }
    }

    val totalPrice: Money
        get() = price * quantity
}

val List<OrderProduct>.sumPrice: Money
    get() = this.map { it.totalPrice }.reduce(Money::plus)

