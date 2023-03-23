package me.devyonghee.auth.persistence

import me.devyonghee.auth.model.Account
import me.devyonghee.auth.model.AccountRepository
import me.devyonghee.auth.persistence.jpa.AccountEntity
import me.devyonghee.auth.persistence.jpa.AccountEntityRepository
import me.devyonghee.common.domain.Email
import org.springframework.stereotype.Repository

@Repository
class AccountDao(
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