package me.devyonghee.commerce.application

import me.devyonghee.commerce.auth.model.Account
import me.devyonghee.commerce.auth.model.Role
import me.devyonghee.commerce.common.domain.Email
import org.springframework.security.crypto.password.PasswordEncoder

val SUPER_ADMIN = Account(
    Email("superadmin@minicommerce.com"),
    "admin",
    listOf(Role.ADMIN)
)

val CUSTOMER = Account(
    Email("customer@minicommerce.com"),
    "customer",
    listOf(Role.CUSTOMER)
)

fun Account.encodePassword(passwordEncoder: PasswordEncoder) = Account(
    email = email,
    password = passwordEncoder.encode(password),
    roles = roles
)