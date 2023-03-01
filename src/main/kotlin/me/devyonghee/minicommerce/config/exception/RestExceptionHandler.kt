package me.devyonghee.minicommerce.config.exception

import me.devyonghee.minicommerce.common.exception.CommerceException
import me.devyonghee.minicommerce.common.exception.ErrorCode
import me.devyonghee.minicommerce.common.ui.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(value = [CommerceException::class])
    fun handleCommerceException(e: CommerceException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(e.statusCode)
            .body(ErrorResponse(e.code, e.message))
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse(ErrorCode.SERVER_ERROR, e.message ?: "internal server error"))
    }
}
