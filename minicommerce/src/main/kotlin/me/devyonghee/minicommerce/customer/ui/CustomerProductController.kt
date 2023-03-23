package me.devyonghee.minicommerce.customer.ui

import me.devyonghee.minicommerce.customer.application.CustomerProductService
import me.devyonghee.minicommerce.customer.ui.response.CustomerProductResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerProductController(
    private val productService: CustomerProductService
) {

    @RequestMapping("/customer/v1/products")
    fun getProducts(): ResponseEntity<List<CustomerProductResponse>> {
        return ResponseEntity.ok(productService.findAll())
    }

    @RequestMapping("/customer/v1/products/{id}")
    fun getProduct(@PathVariable id: Long): ResponseEntity<CustomerProductResponse> {
        return ResponseEntity.ok(productService.product(id))
    }
}