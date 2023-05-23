package com.example.springbootexperimenting.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
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

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<CustomErrorResponse> {
        val errors = ex.bindingResult.allErrors
            .groupBy { (it as FieldError).field }
            .mapValues { it.value.map { error -> error.defaultMessage.orEmpty() } }


        return ResponseEntity.badRequest().body(
            CustomErrorResponse(
                message = "Validation failed",
                status = HttpStatus.BAD_REQUEST.value(),
                errors = errors
            )
        )
    }
}

data class CustomErrorResponse(
    val message: String,
    val status: Int,
    val errors: Map<String, List<String>> = HashMap()
)