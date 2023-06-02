package me.devyonghee.catalog.persistence

import me.devyonghee.catalog.domain.Catalog
import me.devyonghee.catalog.domain.CatalogRepository
import me.devyonghee.catalog.persistence.jpa.CatalogJpaRepository
import org.springframework.stereotype.Repository

@Repository
internal class CatalogDao(
    private val jpaRepository: CatalogJpaRepository
) : CatalogRepository {

    override fun findByProductId(productId: String): Catalog? {
        return jpaRepository.findByProductId(productId)?.toDomain()
    }
}