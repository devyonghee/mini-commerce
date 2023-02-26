package me.devyonghee.minicommerce.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

open class CommerceException(
    val code: ErrorCode,
    override val message: String,
    val statusCode: HttpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR,
    override val cause: Throwable?,
) : RuntimeException() {

}