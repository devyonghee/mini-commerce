package me.devyonghee.minicommerce.common.ui.response

import me.devyonghee.minicommerce.common.exception.ErrorCode

data class ErrorResponse(
    val code: ErrorCode,
    val message: String,
)