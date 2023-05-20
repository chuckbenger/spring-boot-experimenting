package com.example.springbootexperimenting.services

import com.example.springbootexperimenting.entities.Tweet
import com.example.springbootexperimenting.models.*
import com.example.springbootexperimenting.repos.TweetRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TweetService(private val tweetRepository: TweetRepository) {
    @Transactional(readOnly = true)
    fun getTweets(): List<TweetResponse> {
        return tweetRepository.findAll().map { it.toResponse() }
    }

    @Transactional(readOnly = true)
    fun getTweet(id: Long): TweetResponse {
        return tweetRepository.findById(id).orElseThrow().toResponse()
    }

    @Transactional
    fun createTweet(tweetRequest: TweetRequest): TweetResponse {
        val tweet = Tweet(message = tweetRequest.message)

        return tweetRepository.save(tweet).toResponse()
    }
}