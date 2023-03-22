package me.devyonghee.auth.model

import me.devyonghee.common.domain.Email

interface AccountRepository {

    fun save(account: Account): Account

    fun findByEmail(email: Email): Account?
}