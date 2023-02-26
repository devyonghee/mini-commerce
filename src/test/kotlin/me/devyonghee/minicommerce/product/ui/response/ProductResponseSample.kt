package me.devyonghee.minicommerce.product.ui.response

import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath


class ProductResponseSample {
    companion object {

        val FIELDS: List<FieldDescriptor> = listOf(
            fieldWithPath("id").description("상품 ID"),
            fieldWithPath("name").description("상품 이름"),
            fieldWithPath("price").description("상품 가격")
        )

        val AMERICANO = ProductResponse(
            id = 1,
            name = "americano",
            price = 1000
        )
    }
}