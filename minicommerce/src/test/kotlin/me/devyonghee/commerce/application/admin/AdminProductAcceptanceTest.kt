package me.devyonghee.commerce.application.admin

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldStartWith
import io.restassured.RestAssured
import io.restassured.module.kotlin.extensions.Extract
import me.devyonghee.commerce.application.CUSTOMER
import me.devyonghee.commerce.application.SUPER_ADMIN
import me.devyonghee.commerce.application.admin.AdminProductAcceptanceStep.Companion.requestProduct
import me.devyonghee.commerce.application.admin.AdminProductAcceptanceStep.Companion.requestProductCreation
import me.devyonghee.commerce.application.admin.AdminProductAcceptanceStep.Companion.requestProductUpdate
import me.devyonghee.commerce.application.admin.AdminProductAcceptanceStep.Companion.requestProducts
import me.devyonghee.commerce.application.admin.ui.request.ProductRequest
import me.devyonghee.commerce.application.admin.ui.response.AdminProductResponse
import me.devyonghee.commerce.application.encodePassword
import me.devyonghee.commerce.auth.model.AccountRepository
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Transactional

@DisplayName("관리자 상품 관리")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminProductAcceptanceTest(
    private val accountRepository: AccountRepository,
    private val passwordEncoder: PasswordEncoder
) : StringSpec() {

    @LocalServerPort
    private var port: Int = 0

    @Transactional
    override suspend fun beforeSpec(spec: Spec) {
        accountRepository.save(SUPER_ADMIN.encodePassword(passwordEncoder))
        accountRepository.save(CUSTOMER.encodePassword(passwordEncoder))
    }

    override suspend fun beforeEach(testCase: TestCase) {
        RestAssured.port = port
    }

    init {
        "관리자는 상품을 관리" {
            RestAssured.authentication = RestAssured.preemptive()
                .basic("superadmin@minicommerce.com", "admin")

            val name = "test"
            val price: Long = 1000

            val created: AdminProductResponse = requestProductCreation(ProductRequest(name, price))
                .Extract {
                    assertSoftly(body().jsonPath()) {
                        statusCode() shouldBe HttpStatus.CREATED.value()
                        header("Location") shouldStartWith "/admin/v1/products/"
                        getLong("id") shouldNotBe null
                        getString("name") shouldBe name
                        getLong("price") shouldBe price
                    }
                    body().`as`(AdminProductResponse::class.java)
                }

            val updatedName = "updatedName"
            val updatedPrice: Long = 2000
            requestProductUpdate(created.id, ProductRequest(updatedName, updatedPrice))
                .Extract {
                    statusCode() shouldBe HttpStatus.NO_CONTENT.value()
                }

            requestProduct(created.id)
                .Extract {
                    assertSoftly(body().jsonPath()) {
                        statusCode() shouldBe HttpStatus.OK.value()
                        getLong("id") shouldBe created.id
                        getString("name") shouldBe updatedName
                        getLong("price") shouldBe updatedPrice
                    }
                }

            requestProducts(PageRequest.ofSize(10))
                .Extract {
                    assertSoftly(body().jsonPath()) {
                        statusCode() shouldBe HttpStatus.OK.value()
                        getLong("totalElements") shouldBe 1
                        getLong("totalPages") shouldBe 1
                        getLong("numberOfElements") shouldBe 1
                        getLong("size") shouldBe 10
                        getLong("number") shouldBe 0
                        getList("content", AdminProductResponse::class.java) shouldHaveSize 1
                        getBoolean("first") shouldBe true
                        getBoolean("last") shouldBe true
                        getBoolean("empty") shouldBe false
                    }
                }
        }

        "일반 사용자는 상품을 관리할 수 없음" {
            RestAssured.authentication = RestAssured.preemptive()
                .basic("customer@minicommerce.com", "customer")

            requestProductCreation(ProductRequest("test", 1000))
                .Extract {
                    statusCode() shouldBe HttpStatus.FORBIDDEN.value()
                }
        }
    }

    override fun afterSpec(f: suspend (Spec) -> Unit) {
        RestAssured.reset()
    }
}