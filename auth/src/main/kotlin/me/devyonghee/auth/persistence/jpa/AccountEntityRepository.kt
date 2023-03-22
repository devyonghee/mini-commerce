package me.devyonghee.auth.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface AccountEntityRepository : JpaRepository<AccountEntity, Long> {

    fun findByEmail(email: String): AccountEntity?
}