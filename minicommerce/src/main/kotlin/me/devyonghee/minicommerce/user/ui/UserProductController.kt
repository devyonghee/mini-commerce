package me.devyonghee.minicommerce.user.ui

import me.devyonghee.minicommerce.user.ui.response.ProductResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/products")
class UserProductController(
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