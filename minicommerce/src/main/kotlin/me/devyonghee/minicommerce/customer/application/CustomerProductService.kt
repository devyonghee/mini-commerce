package me.devyonghee.minicommerce.customer.application

import me.devyonghee.minicommerce.customer.ui.response.CustomerProductResponse
import me.devyonghee.product.application.ProductService
import org.springframework.data.domain.PageRequest

class CustomerProductService(
    private val productService: ProductService
) {
    fun findAll(): List<CustomerProductResponse> {
        return productService.products(PageRequest.ofSize(100))
            .map { CustomerProductResponse(it) }
            .toList()
    }

    fun product(id: Long): CustomerProductResponse {
        return CustomerProductResponse(productService.product(id))
    }
}