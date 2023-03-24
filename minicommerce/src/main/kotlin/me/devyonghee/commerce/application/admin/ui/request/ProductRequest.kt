package me.devyonghee.commerce.application.admin.ui.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import me.devyonghee.commerce.common.domain.Money
import me.devyonghee.commerce.product.domain.Product

data class ProductRequest(
    @NotBlank
    val name: String,
    @Min(0)
    val price: Long
) {
    fun toDomain(): Product {
        return Product(name, Money(price))
    }

}
