package com.example.springbootexperimenting.models

import com.example.springbootexperimenting.entities.User

data class RegisterUser(
    val email: String,
    val username: String,
    val password: String
)

data class LoginUser(
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String
)

data class UserResponse(
    val id: Long,
    val email: String,
    val username: String
)

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        email = email,
        username = username
    )
}