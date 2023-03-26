package me.devyonghee.commerce.application.admin.ui

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.devyonghee.commerce.application.CUSTOMER
import me.devyonghee.commerce.application.SUPER_ADMIN
import me.devyonghee.commerce.application.admin.ui.request.ProductRequest
import me.devyonghee.commerce.application.admin.ui.response.AdminProductResponse
import me.devyonghee.commerce.application.config.security.AccountUserDetails
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.notNullValue
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("상품 관리 API 테스트")
class AdminProductControllerTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) : StringSpec({

    fun createdProduct(request: ProductRequest): AdminProductResponse {
        val response = mockMvc.post("/admin/v1/products") {
            with(user(AccountUserDetails(SUPER_ADMIN)))
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andReturn()
            .response
            .contentAsString

        return objectMapper.readValue(response, AdminProductResponse::class.java)
    }

    fun product(id: Long): AdminProductResponse {
        val response = mockMvc.get("/admin/v1/products/$id") {
            with(user(AccountUserDetails(SUPER_ADMIN)))
            contentType = MediaType.APPLICATION_JSON
        }.andReturn()
            .response
            .contentAsString

        return objectMapper.readValue(response, AdminProductResponse::class.java)
    }

    "상품 생성" {
        mockMvc.post("/admin/v1/products") {
            with(user(AccountUserDetails(SUPER_ADMIN)))
            contentType = MediaType.APPLICATION_JSON
            content = """{"name": "test", "price": 1000}"""
        }.andExpect {
            status { isCreated() }
            content {
                jsonPath("id") { notNullValue() }
            }
        }
    }

    "상품 수정" {
        //given
        val createdProduct: AdminProductResponse = createdProduct(ProductRequest("test", 1000))
        val updatedName = "updatedName"
        val updatedPrice = 2000
        //when
        mockMvc.put("/admin/v1/products/${createdProduct.id}") {
            with(user(AccountUserDetails(SUPER_ADMIN)))
            contentType = MediaType.APPLICATION_JSON
            content = """{"name": "$updatedName", "price": $updatedPrice}"""
        }.andExpect {
            status { isNoContent() }
        }

        //then
        product(createdProduct.id).run {
            name shouldBe updatedName
            price shouldBe updatedPrice
        }
    }

    "상품 조회" {
        //given
        val createdProduct: AdminProductResponse = createdProduct(ProductRequest("test", 1000))
        //when
        mockMvc.get("/admin/v1/products/${createdProduct.id}") {
            with(user(AccountUserDetails(SUPER_ADMIN)))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content {
                jsonPath("id") { value(createdProduct.id) }
                jsonPath("name") { value(createdProduct.name) }
                jsonPath("price") { value(createdProduct.price) }
                jsonPath("createdAt") { notNullValue() }
                jsonPath("createdBy") { notNullValue() }
                jsonPath("updatedAt") { notNullValue() }
                jsonPath("updatedBy") { notNullValue() }
            }
        }
    }

    "상품 리스트 조회" {
        //given
        createdProduct(ProductRequest("test", 1000))
        //when
        mockMvc.get("/admin/v1/products") {
            with(user(AccountUserDetails(SUPER_ADMIN)))
            param("page", "0")
            param("size", "10")
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content {
                jsonPath("content") { notNullValue() }
                jsonPath("totalPages") { greaterThan(0) }
                jsonPath("totalElements") { greaterThan(0) }
                jsonPath("size") { value(10) }
                jsonPath("empty") { value(false) }
            }
        }
    }

    "일반 유저는 상품을 등록할 수 없음" {
        mockMvc.post("/admin/v1/products") {
            with(user(AccountUserDetails(CUSTOMER)))
            contentType = MediaType.APPLICATION_JSON
            content = """{"name": "test", "price": 1000}"""
        }.andExpect {
            status { isForbidden() }
        }
    }
})