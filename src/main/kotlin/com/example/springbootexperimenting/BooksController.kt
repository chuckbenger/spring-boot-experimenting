package com.example.springbootexperimenting

import com.example.springbootexperimenting.models.BookDTO
import com.example.springbootexperimenting.services.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BooksController(private val bookService: BookService) {

    @GetMapping("/")
    fun getBooks(): ResponseEntity<*> {
        return ResponseEntity.ok(bookService.getBooks())
    }

    @GetMapping("/{id}")
    fun getBook(@PathVariable id: Long): ResponseEntity<*> {
        return ResponseEntity.ok(bookService.getBook(id))
    }

    @PostMapping("/")
    fun createBook(@RequestBody bookDTO: BookDTO): ResponseEntity<*> {
        return ResponseEntity
            .status(201)
            .body(bookService.createBook(bookDTO))
    }
}