package me.devyonghee.catalog.domain

import java.time.LocalDateTime

data class Catalog(
    val id: Long,
    val productId: String,
    val productName: String,
    val stock: Int,
    val unitPrice: Int,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
