package me.devyonghee.minicommerce.product.ui

import me.devyonghee.minicommerce.RestDocConfiguration
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureRestDocs
@Import(RestDocConfiguration::class)
@ExtendWith(RestDocumentationExtension::class)
@WebMvcTest(value = [ProductController::class])
class ProductControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {


}