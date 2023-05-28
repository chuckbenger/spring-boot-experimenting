package com.example.springbootexperimenting.dto

import com.example.springbootexperimenting.entities.Tweet
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

import java.time.LocalDateTime

data class TweetRequest(
    @field:NotNull
    @field:Size(min = 1, max = 255)
    val message: String
)

data class TweetResponse(
    val id: Long,
    val message: String,
    val upVotes: Int,
    val downVotes: Int,
    val createdAt: LocalDateTime
)

fun Tweet.toResponse(): TweetResponse {
    return TweetResponse(
        id = id!!,
        message = message,
        upVotes = upVotes,
        downVotes = downVotes,
        createdAt = createdAt!!
    )
}