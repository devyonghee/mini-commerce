package me.devyonghee.commerce.application

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.test.AssertionMode
import io.kotest.extensions.spring.SpringExtension

class KotestSpringConfig : AbstractProjectConfig() {

    override val assertionMode = AssertionMode.Error
    override fun extensions() = listOf(SpringExtension)
}