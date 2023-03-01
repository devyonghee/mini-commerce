package me.devyonghee.minicommerce.auth.domain

import me.devyonghee.minicommerce.common.domain.Email
import org.springframework.data.jpa.repository.JpaRepository

interface EmailPasswordAccountRepository : JpaRepository<EmailPasswordAccount, Long> {

    fun findByEmail(email: Email): EmailPasswordAccount?
}