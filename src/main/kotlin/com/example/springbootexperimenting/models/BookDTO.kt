package com.example.springbootexperimenting.models

import com.example.springbootexperimenting.entities.Book

data class BookDTO(
    val id: Long,
    val title: String,
    var author: String,
    var isbn: String,
)

fun BookDTO.toBook(): Book {
    return Book(
        title = this.title,
        author = this.author,
        isbn = this.isbn,
        id = this.id,
    )
}

fun Book.toBookDTO(): BookDTO {
    return BookDTO(
        title = this.title,
        author = this.author,
        isbn = this.isbn,
        id = this.id!!,
    )
}