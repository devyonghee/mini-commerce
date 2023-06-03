package me.devyonghee.catalog.domain

interface CatalogRepository {

    fun findAll(): Collection<Catalog>

    fun findByProductId(productId: String): Catalog?
}
