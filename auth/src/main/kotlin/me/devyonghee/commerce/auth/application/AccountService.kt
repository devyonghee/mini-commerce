package me.devyonghee.commerce.auth.application

import me.devyonghee.commerce.auth.model.Account
import me.devyonghee.commerce.auth.model.AccountRepository
import me.devyonghee.commerce.common.domain.Email
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {

    fun create(account: Account): Account {
        return accountRepository.save(account)
    }

    fun isExists(email: Email): Boolean {
        return accountRepository.findByEmail(email) != null
    }

    fun account(email: Email): Account {
        return accountRepository.findByEmail(email)
            ?: throw IllegalArgumentException("user not exists: email(`$email`)")
    }
}