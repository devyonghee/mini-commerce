package me.devyonghee.minicommerce.auth.application

import me.devyonghee.minicommerce.auth.domain.EmailPasswordAccount
import me.devyonghee.minicommerce.auth.domain.EmailPasswordAccountRepository
import me.devyonghee.minicommerce.common.domain.Email
import me.devyonghee.minicommerce.common.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class EmailPasswordAccountService(
    private val accountRepository: EmailPasswordAccountRepository,
) {
    fun account(email: Email): EmailPasswordAccount {
        return accountRepository.findByEmail(email)
            ?: throw NotFoundException("account(email: '$email') is not exists")
    }
}