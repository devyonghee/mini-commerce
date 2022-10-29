package com.yongpang.product.ui.request

import com.yongpang.product.domain.Product
import com.yongpang.product.domain.ProductName
import com.yongpang.product.domain.ProductPrice
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ProductCreation(
    @NotBlank
    val name: String,
    @NotNull
    val price: BigDecimal
) {
    fun toEntity(): Product {
        return Product(ProductName(name), ProductPrice(price));
    }
}
