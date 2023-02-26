package me.devyonghee.minicommerce.product.ui

import me.devyonghee.minicommerce.product.application.ProductService
import me.devyonghee.minicommerce.product.ui.response.ProductResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/products")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping
    fun getProducts(): ResponseEntity<List<ProductResponse>> {
        return ResponseEntity.ok(productService.products())
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        return ResponseEntity.ok(productService.product(id))
    }
}