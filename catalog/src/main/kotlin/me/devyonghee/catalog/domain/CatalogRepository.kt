package me.devyonghee.catalog.domain

interface CatalogRepository {

    fun findByProductId(productId: String): Catalog?
}
