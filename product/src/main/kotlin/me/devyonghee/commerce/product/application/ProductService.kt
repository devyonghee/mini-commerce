package me.devyonghee.commerce.product.application

import me.devyonghee.commerce.product.domain.Product
import me.devyonghee.commerce.product.domain.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    fun create(product: Product): Product {
        return productRepository.save(product)
    }

    fun products(pageable: Pageable): Page<Product> {
        return productRepository.findAll(pageable)
    }

    fun product(id: Long): Product {
        return productRepository.findById(id) ?: throw NoSuchElementException()
    }
}
