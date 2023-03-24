package me.devyonghee.commerce.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["me.devyonghee.commerce"])
class MiniCommerceApplication

fun main(args: Array<String>) {
    runApplication<MiniCommerceApplication>(*args)
}
