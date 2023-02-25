package me.devyonghee.minicommerce.product.application

import me.devyonghee.minicommerce.product.domain.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

}