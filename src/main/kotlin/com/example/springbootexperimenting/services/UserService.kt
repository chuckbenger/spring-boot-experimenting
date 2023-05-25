package com.example.springbootexperimenting.services

import com.example.springbootexperimenting.entities.User
import com.example.springbootexperimenting.jwt.JWTUtil
import com.example.springbootexperimenting.models.LoginResponse
import com.example.springbootexperimenting.models.LoginUser
import com.example.springbootexperimenting.models.RegisterUser
import com.example.springbootexperimenting.repos.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JWTUtil,
) {
    @Transactional(readOnly = true)
    fun getCurrentUser(): User {
        val currentUserName = SecurityContextHolder.getContext().authentication.name

        return userRepository.findByUsername(currentUserName)
            ?: throw RuntimeException("User $currentUserName not found")
    }

    @Transactional(readOnly = true)
    fun loginUser(loginUser: LoginUser): LoginResponse {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginUser.username, loginUser.password)
        )
        val user = authentication.principal as UserDetails
        val token = jwtUtil.issueToken(user.username)

        return LoginResponse(token)
    }

    @Transactional
    fun createUser(user: RegisterUser): User {
        val newUser = User(
            user.username,
            passwordEncoder.encode(user.password),
            user.email
        )
        return userRepository.save(newUser)
    }
}