package me.devyonghee.commerce.application

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension

class KotestSpringConfig : AbstractProjectConfig() {

    override fun extensions() = listOf(SpringExtension)
}