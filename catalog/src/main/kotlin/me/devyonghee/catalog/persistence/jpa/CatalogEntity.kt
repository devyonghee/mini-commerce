package me.devyonghee.catalog.persistence.jpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import me.devyonghee.catalog.domain.Catalog

@Entity
@Table(name = "catalog")
class CatalogEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,
    @Column(nullable = false, length = 120, unique = true)
    private val productId: String,
    @Column(nullable = false)
    private val productName: String,
    @Column(nullable = false)
    private val stock: String,
    @Column(nullable = false)
    private val unitPrice: String,
    @Column(nullable = false, updatable = false)
    private val createdAt: LocalDateTime,
) {

    constructor(catalog: Catalog) : this(
        id = catalog.id,
        productId = catalog.productId,
        productName = catalog.productName,
        stock = catalog.stock,
        unitPrice = catalog.unitPrice,
        createdAt = catalog.createdAt
    )

    fun toDomain() = Catalog(
        id = id,
        productId = productId,
        productName = productName,
        stock = stock,
        unitPrice = unitPrice,
        createdAt = createdAt
    )
}