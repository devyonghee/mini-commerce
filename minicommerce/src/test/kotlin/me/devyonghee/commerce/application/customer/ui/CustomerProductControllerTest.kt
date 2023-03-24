package me.devyonghee.commerce.application.customer.ui

import me.devyonghee.commerce.application.MiniCommerceApplicationKtTest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@AutoConfigureMockMvc
class CustomerProductControllerTest : MiniCommerceApplicationKtTest() {

    lateinit var mockMvc: MockMvc

    @Test
    fun product() {
        mockMvc.perform(get("/customer/v1/products/{id}", 1))
            .andExpect(status().isOk)
    }

    @Test
    fun products() {
        mockMvc.perform(get("/customer/v1/products"))
            .andExpect(status().isOk)
    }
}