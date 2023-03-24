package me.devyonghee.commerce.auth.model

import me.devyonghee.commerce.common.domain.Email

interface AccountRepository {

    fun save(account: Account): Account

    fun findByEmail(email: Email): Account?
}