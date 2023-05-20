package com.example.springbootexperimenting.models

import com.example.springbootexperimenting.entities.Tweet
import java.time.LocalDateTime

data class TweetRequest(val message: String)
data class TweetResponse(
    val id: Long,
    val message: String,
    val upVotes: Int,
    val downVotes: Int,
    val createdAt: LocalDateTime
)

fun Tweet.toResponse(): TweetResponse {
    return TweetResponse(
        id = this.id!!,
        message = this.message,
        upVotes = this.upVotes,
        downVotes = this.downVotes,
        createdAt = this.createdAt!!
    )
}