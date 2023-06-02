package me.devyonghee.catalog.domain

import java.time.LocalDateTime

class Catalog(
    val id: Long,
    val productId: String,
    val productName: String,
    val stock: String,
    val unitPrice: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {

}
