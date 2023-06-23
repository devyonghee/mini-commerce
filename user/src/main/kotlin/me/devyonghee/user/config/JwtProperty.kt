package me.devyonghee.user.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties("token")
data class JwtProperty @ConstructorBinding constructor(
    val secret: String,
    val expirationTime: Long
)
