package me.devyonghee.catalog.controller

import com.fasterxml.jackson.annotation.JsonInclude
import me.devyonghee.catalog.domain.Catalog
import me.devyonghee.catalog.service.CatalogService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/catalog-service")
class CatalogController(
    private val catalogService: CatalogService
) {

    @GetMapping("/catalogs")
    fun catalogs(): Collection<CatalogResponse> {
        return catalogService.catalogs()
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class CatalogResponse(
        val productId: String,
        val productName: String,
        val stock: Int,
        val unitPrice: Int,
        val createdAt: LocalDateTime
    ) {
        constructor(catalog: Catalog) : this(
            productId = catalog.productId,
            productName = catalog.productName,
            unitPrice = catalog.unitPrice,
            stock = catalog.stock,
            createdAt = catalog.createdAt
        )
    }
}
