package me.devyonghee.commerce.auth.persistence

import me.devyonghee.commerce.auth.model.Account
import me.devyonghee.commerce.auth.model.AccountRepository
import me.devyonghee.commerce.auth.persistence.jpa.AccountEntity
import me.devyonghee.commerce.auth.persistence.jpa.AccountEntityRepository
import me.devyonghee.commerce.common.domain.Email
import org.springframework.stereotype.Repository

@Repository
internal class AccountDao(
    private val jpaRepository: AccountEntityRepository
) : AccountRepository {

    override fun save(account: Account): Account {
        return jpaRepository.save(AccountEntity(account))
            .toDomain()
    }

    override fun findByEmail(email: Email): Account? {
        return jpaRepository.findByEmail(email.toString())
            ?.toDomain()
    }
}