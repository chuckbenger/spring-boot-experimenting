package com.example.springbootexperimenting.models

import com.example.springbootexperimenting.entities.Tweet
import java.time.LocalDateTime

data class TweetDTO(
    val id: Long,
    val message: String,
    val upVotes: Int,
    val downVotes: Int,
    val createdAt: LocalDateTime
)

fun TweetDTO.toEntity(): Tweet {
    return Tweet(message = this.message)
}

fun Tweet.toDTO(): TweetDTO {
    return TweetDTO(
        id = this.id!!,
        message = this.message,
        upVotes = this.upVotes,
        downVotes = this.downVotes,
        createdAt = this.createdAt!!
    )
}