package me.devyonghee.commerce.application.admin.appilcation

import me.devyonghee.commerce.application.admin.ui.request.ProductRequest
import me.devyonghee.commerce.application.admin.ui.response.AdminProductResponse
import me.devyonghee.commerce.product.application.ProductService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AdminProductService(
    private val productService: ProductService
) {
    @Transactional
    fun create(request: ProductRequest): AdminProductResponse {
        return AdminProductResponse(productService.create(request.toDomain()))
    }

    fun product(id: Long): AdminProductResponse {
        return AdminProductResponse(productService.product(id))
    }

    fun products(page: Pageable): Page<AdminProductResponse> {
        return productService.products(page)
            .map { AdminProductResponse(it) }
    }

    fun update(id: Long, request: ProductRequest) {
        productService.update(id, request.toDomain())
    }
}