package com.yongpang.product.application

import com.yongpang.product.domain.Product
import com.yongpang.product.domain.ProductRepository
import com.yongpang.product.ui.request.ProductCreation
import com.yongpang.product.ui.response.ProductResponse
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun create(creation: ProductCreation): ProductResponse {
        return ProductResponse(productRepository.save(creation.toEntity()));
    }
}
