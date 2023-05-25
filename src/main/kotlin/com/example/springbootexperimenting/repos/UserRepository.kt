package com.example.springbootexperimenting.repos

import com.example.springbootexperimenting.entities.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun findByEmail(email: String): User?
}