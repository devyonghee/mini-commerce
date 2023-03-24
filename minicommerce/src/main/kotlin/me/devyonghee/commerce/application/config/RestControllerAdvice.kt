package me.devyonghee.commerce.application.config

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class RestControllerAdvice {

    private val log = LoggerFactory.getLogger(RestControllerAdvice::class.java)

    @ExceptionHandler(value = [Exception::class])
    fun handleException(e: Exception): ResponseEntity<ProblemDetail> {
        log.error(e.message, e)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.toString()))
    }
}