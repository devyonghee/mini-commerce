package me.devyonghee.product.application

import me.devyonghee.product.domain.Product
import me.devyonghee.product.domain.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    fun products(pageable: Pageable): Page<Product> {
        return productRepository.findAll(pageable)
    }

    fun product(id: Long): Product {
        return productRepository.findById(id) ?: throw NoSuchElementException()
    }
}
