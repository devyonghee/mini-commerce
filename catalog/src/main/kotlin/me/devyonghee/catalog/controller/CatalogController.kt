package me.devyonghee.catalog.controller

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime
import org.springframework.web.bind.annotation.RestController

@RestController
class CatalogController {

    class CatalogRequest(
        var productId: String,
        var productName: String,
        var stock: String,
        var unitPrice: String,
    )

    @JsonInclude(JsonInclude.Include.NON_NULL)
    class CatalogResponse(
        var productId: String,
        var productName: String,
        var unitPrice: String,
        var stock: Int,
        var createdAt: LocalDateTime,
    )
}