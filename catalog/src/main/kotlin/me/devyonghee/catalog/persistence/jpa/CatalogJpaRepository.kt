package me.devyonghee.catalog.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository

internal interface CatalogJpaRepository : JpaRepository<CatalogEntity, Long> {

    fun findByProductId(productId: String): CatalogEntity?
}
