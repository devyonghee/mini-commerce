package me.devyonghee.minicommerce.product.ui

import me.devyonghee.minicommerce.RestDocConfiguration
import me.devyonghee.minicommerce.product.application.ProductService
import me.devyonghee.minicommerce.product.ui.response.ProductResponseSample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@DisplayName("상품 api")
@AutoConfigureRestDocs
@Import(RestDocConfiguration::class)
@ExtendWith(RestDocumentationExtension::class)
@WebMvcTest(value = [ProductController::class])
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var productService: ProductService

    @Test
    @DisplayName("상품 리스트 조회")
    fun get_products_200() {
        // given
        given(productService.products())
            .willReturn(listOf(ProductResponseSample.AMERICANO))
        // when & then
        mockMvc.perform(get("/v1/products"))
            .andExpect(status().isOk)
            .andDo(
                document(
                    "products/list",
                    responseFields(applyPathPrefix("[].", ProductResponseSample.FIELDS))
                )
            )
    }

    @Test
    @DisplayName("상품 조회")
    fun get_product_200() {
        // given
        val id: Long = 1
        given(productService.product(id))
            .willReturn(ProductResponseSample.AMERICANO)
        // when & then
        mockMvc.perform(get("/v1/products/{id}", id))
            .andExpect(status().isOk)
            .andDo(
                document(
                    "products/one",
                    pathParameters(parameterWithName("id").description("상품 ID")),
                    responseFields(ProductResponseSample.FIELDS)
                )
            )
    }

}