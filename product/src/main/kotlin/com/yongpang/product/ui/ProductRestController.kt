package com.yongpang.product.ui

import com.yongpang.product.application.ProductService
import com.yongpang.product.ui.request.ProductCreation
import com.yongpang.product.ui.response.ProductResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/products")
class ProductRestController(private val service: ProductService) {

    @PostMapping
    fun create(request: ProductCreation): ResponseEntity<ProductResponse> {
        val response: ProductResponse = service.create(request);
        return ResponseEntity.created(URI("/products/${response.id}"))
            .body(response);
    }
}
