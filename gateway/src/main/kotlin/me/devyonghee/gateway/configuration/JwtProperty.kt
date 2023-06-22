package me.devyonghee.gateway.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding


@ConfigurationProperties("token")
data class JwtProperty @ConstructorBinding constructor(
    val secret: String,
)