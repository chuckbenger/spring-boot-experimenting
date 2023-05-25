package com.example.springbootexperimenting.controllers

import com.example.springbootexperimenting.entities.User
import com.example.springbootexperimenting.models.*
import com.example.springbootexperimenting.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestBody registerUser: RegisterUser): ResponseEntity<UserResponse> {
        val newUser = userService.createUser(registerUser)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(convertToUserResponse(newUser))
    }

    @PostMapping("/login")
    fun login(@RequestBody loginUser: LoginUser): ResponseEntity<LoginResponse> {
        val response = userService.loginUser(loginUser)

        return ResponseEntity.ok(response)
    }

    private fun convertToUserResponse(user: User) = user.toResponse()
}