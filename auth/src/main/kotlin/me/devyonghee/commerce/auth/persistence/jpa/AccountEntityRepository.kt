package me.devyonghee.commerce.auth.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository

internal interface AccountEntityRepository : JpaRepository<AccountEntity, Long> {
    fun findByEmail(email: String): AccountEntity?
}