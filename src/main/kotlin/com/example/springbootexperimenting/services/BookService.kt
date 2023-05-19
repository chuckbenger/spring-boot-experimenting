package com.example.springbootexperimenting.services

import com.example.springbootexperimenting.entities.Book
import com.example.springbootexperimenting.models.BookDTO
import com.example.springbootexperimenting.models.toBook
import com.example.springbootexperimenting.models.toBookDTO
import com.example.springbootexperimenting.repos.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(private val bookRepository: BookRepository) {

    @Transactional(readOnly = true)
    fun getBooks(): List<BookDTO> {
        return bookRepository.findAll().map { it.toBookDTO() }
    }

    @Transactional(readOnly = true)
    fun getBook(id: Long): BookDTO {
        return bookRepository.findById(id).get().toBookDTO()
    }

    @Transactional
    fun createBook(bookDTO: BookDTO): BookDTO {
        return bookRepository.save(bookDTO.toBook()).toBookDTO()
    }
}