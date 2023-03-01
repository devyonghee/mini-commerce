package me.devyonghee.minicommerce.product.ui.response

import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath


class ProductResponseSample {
    companion object {

        val FIELDS: List<FieldDescriptor> = listOf(
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("상품 ID"),
            fieldWithPath("name").type(JsonFieldType.STRING).description("상품 이름"),
            fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품 가격")
        )

        val AMERICANO = ProductResponse(
            id = 1,
            name = "americano",
            price = 1000
        )
    }
}