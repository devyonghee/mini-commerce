package me.devyonghee.product.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductRepository {

    fun findById(id: Long): Product?
    fun findAll(pageable: Pageable): Page<Product>
}