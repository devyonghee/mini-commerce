package com.yongpang.product.application

import com.yongpang.product.domain.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

}
