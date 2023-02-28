package me.devyonghee.minicommerce.order.domain

import jakarta.persistence.*
import me.devyonghee.minicommerce.common.domain.Money

@Entity(name = "orders")
class Order(
    private val memberId: Long,
    @Enumerated(EnumType.STRING)
    private val status: OrderStatus = OrderStatus.CREATED,
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    private val products: List<OrderProduct>,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {
    @Embedded
    private val totalPrice: Money = products.sumPrice

    init {
        require(products.isNotEmpty()) { "products must not be empty" }
    }

}