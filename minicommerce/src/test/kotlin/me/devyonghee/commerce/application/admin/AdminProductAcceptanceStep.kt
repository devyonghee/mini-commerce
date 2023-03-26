package me.devyonghee.commerce.application.admin

import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.ValidatableResponse
import me.devyonghee.commerce.application.admin.ui.request.ProductRequest
import org.springframework.data.domain.Pageable

class AdminProductAcceptanceStep {
    companion object {

        fun requestProductCreation(request: ProductRequest = ProductRequest("name", 1000)): ValidatableResponse {
            return Given {
                contentType(ContentType.JSON)
                body(request)

            }.When {
                log().all()
                post("/admin/v1/products")
            }.Then { log().all() }
        }

        fun requestProductUpdate(id: Long, productRequest: ProductRequest): ValidatableResponse {
            return Given {
                contentType(ContentType.JSON)
                body(productRequest)

            }.When {
                log().all()
                put("/admin/v1/products/${id}")
            }.Then { log().all() }
        }

        fun requestProduct(id: Long): ValidatableResponse {
            return Given {
                contentType(ContentType.JSON)
            }.When {
                log().all()
                get("/admin/v1/products/${id}")
            }.Then { log().all() }
        }

        fun requestProducts(page: Pageable): ValidatableResponse {
            return Given {
                contentType(ContentType.JSON)

            }.When {
                log().all()
                queryParams(
                    mutableMapOf(
                        "page" to page.pageNumber,
                        "size" to page.pageSize,
                    )
                )
                get("/admin/v1/products")
            }.Then { log().all() }
        }
    }
}