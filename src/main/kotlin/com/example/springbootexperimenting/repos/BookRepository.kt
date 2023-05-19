package com.example.springbootexperimenting.repos

import com.example.springbootexperimenting.entities.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long>