package me.devyonghee.commerce.application.admin.ui

import me.devyonghee.commerce.application.admin.appilcation.AdminProductService
import me.devyonghee.commerce.application.admin.ui.request.ProductRequest
import me.devyonghee.commerce.application.admin.ui.response.AdminProductResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class AdminProductController(
    private val adminProductService: AdminProductService
) {

    @PostMapping("/admin/v1/products")
    fun create(@Validated @RequestBody request: ProductRequest): ResponseEntity<AdminProductResponse> {
        val product = adminProductService.create(request)
        return ResponseEntity.created(URI("/admin/v1/products/${product.id}")).body(product)
    }

    @PostMapping("/admin/v1/products")
    fun update(@Validated @RequestBody request: ProductRequest): ResponseEntity<AdminProductResponse> {
        val product = adminProductService.create(request)
        return ResponseEntity.created(URI("/admin/v1/products/${product.id}")).body(product)
    }

    @GetMapping("/admin/v1/products/{id}")
    fun product(@PathVariable id: Long): ResponseEntity<AdminProductResponse> {
        return ResponseEntity.ok(adminProductService.product(id))
    }

    @GetMapping("/admin/v1/products")
    fun products(pageRequest: Pageable): ResponseEntity<Page<AdminProductResponse>> {
        return ResponseEntity.ok(adminProductService.products(pageRequest))
    }
}