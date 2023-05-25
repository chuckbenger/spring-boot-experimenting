package com.example.springbootexperimenting.services

import com.example.springbootexperimenting.repos.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user =
            userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User $username not found")

        return object : UserDetails {
            override fun getAuthorities() = listOf(SimpleGrantedAuthority("USER"))

            override fun getPassword() = user.password

            override fun getUsername() = user.username

            override fun isAccountNonExpired() = true

            override fun isAccountNonLocked() = true

            override fun isCredentialsNonExpired() = true

            override fun isEnabled() = true
        }
    }
}