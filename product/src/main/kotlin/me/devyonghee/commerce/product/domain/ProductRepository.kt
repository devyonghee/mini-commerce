package me.devyonghee.commerce.product.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductRepository {

    fun save(product: Product): Product

    fun findById(id: Long): Product?

    fun findAll(pageable: Pageable): Page<Product>
}