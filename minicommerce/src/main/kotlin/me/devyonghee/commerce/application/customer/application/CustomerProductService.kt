package me.devyonghee.commerce.application.customer.application

import me.devyonghee.commerce.application.customer.ui.response.CustomerProductResponse
import me.devyonghee.commerce.product.application.ProductService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
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