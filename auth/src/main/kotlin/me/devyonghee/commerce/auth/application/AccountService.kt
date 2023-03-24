package me.devyonghee.commerce.auth.application

import me.devyonghee.commerce.auth.model.AccountRepository
import me.devyonghee.commerce.common.domain.Email
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {

    fun create(account: me.devyonghee.commerce.auth.model.Account): me.devyonghee.commerce.auth.model.Account {
        return accountRepository.save(account)
    }

    fun findByEmail(email: String): me.devyonghee.commerce.auth.model.Account? {
        return accountRepository.findByEmail(Email(email))
    }
}