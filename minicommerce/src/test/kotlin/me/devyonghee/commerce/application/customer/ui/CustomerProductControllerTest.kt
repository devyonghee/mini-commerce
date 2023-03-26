package me.devyonghee.commerce.application.customer.ui

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import me.devyonghee.commerce.application.SUPER_ADMIN
import me.devyonghee.commerce.application.admin.ui.response.AdminProductResponse
import me.devyonghee.commerce.application.config.security.AccountUserDetails
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("고객 상품 API 테스트")
class CustomerProductControllerTest(
    private val mockMvc: MockMvc,
    private val mapper: ObjectMapper
) : StringSpec({

    var createdProduct: AdminProductResponse? = null

    beforeSpec {
        createdProduct = mapper.readValue(
            mockMvc.post("/admin/v1/products") {
                with(user(AccountUserDetails(SUPER_ADMIN)))
                contentType = MediaType.APPLICATION_JSON
                content = """{"name": "test", "price": 1000}"""
            }.andReturn()
                .response
                .contentAsString,
            AdminProductResponse::class.java
        )
    }

    "상품 조회" {
        mockMvc.get("/customer/v1/products/${createdProduct?.id}")
            .andExpect {
                status { isOk() }
                content {
                    jsonPath("name") { value(createdProduct?.name) }
                    jsonPath("price") { value(createdProduct?.price) }
                }
            }
    }

    "상품 목록 조회" {
        mockMvc.get("/customer/v1/products")
            .andExpect {
                status { isOk() }
                content {
                    jsonPath("$[0].name") { value(createdProduct?.name) }
                    jsonPath("$[0].price") { value(createdProduct?.price) }
                }
            }
    }
})