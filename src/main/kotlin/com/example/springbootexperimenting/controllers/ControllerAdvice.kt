package com.example.springbootexperimenting.controllers

import jakarta.validation.ValidationException
import liquibase.repackaged.net.sf.jsqlparser.util.validation.ValidationError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<CustomErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(CustomErrorResponse(e.message ?: "Not found", HttpStatus.NOT_FOUND.value()))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleValidationError(e: HttpMessageNotReadableException): ResponseEntity<CustomErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(CustomErrorResponse(e.message ?: "Bad Request", HttpStatus.BAD_REQUEST.value()))
    }
}

data class CustomErrorResponse(
    val message: String,
    val status: Int
)