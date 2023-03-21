package me.devyonghee.product.persistence

import me.devyonghee.product.domain.Product
import me.devyonghee.product.domain.ProductRepository
import me.devyonghee.product.persistence.jpa.ProductEntityRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
internal class ProductDao(
    private val productEntityRepository: ProductEntityRepository
) : ProductRepository {
    override fun findById(id: Long): Product? {
        return productEntityRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun findAll(pageable: Pageable): Page<Product> {
        return productEntityRepository.findAll(pageable)
            .map { it.toDomain() }
    }
}