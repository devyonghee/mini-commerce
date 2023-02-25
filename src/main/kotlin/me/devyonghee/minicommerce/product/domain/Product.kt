package me.devyonghee.minicommerce.product.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import me.devyonghee.minicommerce.common.domain.Money

@Entity
class Product(
    var name: String,
    var price: Money,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) {
}