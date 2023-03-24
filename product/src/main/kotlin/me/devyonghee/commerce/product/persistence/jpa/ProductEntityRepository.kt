package me.devyonghee.commerce.product.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface ProductEntityRepository : JpaRepository<ProductEntity, Long> {
}