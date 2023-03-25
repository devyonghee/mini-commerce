package me.devyonghee.commerce.product.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository

internal interface ProductEntityRepository : JpaRepository<ProductEntity, Long> {
}