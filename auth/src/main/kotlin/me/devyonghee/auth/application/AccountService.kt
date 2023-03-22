package me.devyonghee.auth.application

import me.devyonghee.auth.model.Account
import me.devyonghee.auth.model.AccountRepository
import me.devyonghee.common.domain.Email
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {

    fun create(account: Account): Account {
        return accountRepository.save(account)
    }

    fun findByEmail(email: String): Account? {
        return accountRepository.findByEmail(Email(email))
    }
}