package com.example.springbootexperimenting.entities

import jakarta.persistence.*

@Entity
class Book(
    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    var author: String,

    @Column(nullable = false)
    var isbn: String,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
)
