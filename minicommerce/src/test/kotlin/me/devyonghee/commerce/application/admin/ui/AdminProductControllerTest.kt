package me.devyonghee.commerce.application.admin.ui

import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldStartWith
import io.restassured.RestAssured
import io.restassured.RestAssured.preemptive
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import me.devyonghee.commerce.application.admin.ui.request.ProductRequest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminProductControllerTest : StringSpec() {

    @LocalServerPort
    private var port: Int = 0

    override suspend fun beforeEach(testCase: TestCase) {
        RestAssured.port = port
        RestAssured.authentication = preemptive()
            .basic("admin@admin.com", "admin")
    }

    init {
        "상품 생성" {
            val name = "test"
            val price: Long = 1000

            Given {
                log().all()
                body(ProductRequest(name, price))

            }.When {
                post("/admin/v1/products")

            }.Then {
                statusCode(HttpStatus.CREATED.value())

            }.Extract {
                header("Location") shouldStartWith "/admin/v1/products/"
                val jsonPath = body().jsonPath()
                jsonPath.getLong("id") shouldNotBe null
                jsonPath.getLong("name") shouldBe name
                jsonPath.getLong("price") shouldBe price
            }
        }
    }
}