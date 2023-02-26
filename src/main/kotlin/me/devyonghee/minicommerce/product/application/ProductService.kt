package me.devyonghee.minicommerce.product.application

import me.devyonghee.minicommerce.common.exception.NotFoundException
import me.devyonghee.minicommerce.product.domain.ProductRepository
import me.devyonghee.minicommerce.product.ui.response.ProductResponse
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun products(): List<ProductResponse> {
        return productRepository.findAll().map { ProductResponse(it) }
    }

    fun product(id: Long): ProductResponse {
        return productRepository.findById(id)
            .orElseThrow {
                NotFoundException("product(id: $id) is not exist")
            }.let { ProductResponse(it) }
    }
}
