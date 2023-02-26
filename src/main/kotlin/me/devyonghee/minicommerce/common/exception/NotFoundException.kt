package me.devyonghee.minicommerce.common.exception

import org.springframework.http.HttpStatus

class NotFoundException(
    override val message: String,
    override val cause: Throwable? = null,
) : CommerceException(ErrorCode.NOT_FOUND, message, HttpStatus.NOT_FOUND, cause)