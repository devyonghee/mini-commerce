package me.devyonghee.commerce.product.persistence

import me.devyonghee.commerce.product.domain.Product
import me.devyonghee.commerce.product.domain.ProductRepository
import me.devyonghee.commerce.product.persistence.jpa.ProductEntity
import me.devyonghee.commerce.product.persistence.jpa.ProductEntityRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
internal class ProductDao(
    private val productEntityRepository: ProductEntityRepository
) : ProductRepository {

    override fun save(product: Product): Product {
        return productEntityRepository.save(ProductEntity(product)).toDomain()
    }

    override fun findById(id: Long): Product? {
        return productEntityRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun findAll(pageable: Pageable): Page<Product> {
        return productEntityRepository.findAll(pageable)
            .map { it.toDomain() }
    }

    override fun update(id: Long, product: Product): Product {
        return productEntityRepository.findByIdOrNull(id)?.apply {
            change(product)
        }?.toDomain() ?: throw NoSuchElementException()
    }
}