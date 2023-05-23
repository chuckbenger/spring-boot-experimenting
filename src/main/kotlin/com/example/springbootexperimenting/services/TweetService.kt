package com.example.springbootexperimenting.services

import com.example.springbootexperimenting.controllers.NotFoundException
import com.example.springbootexperimenting.entities.Tweet
import com.example.springbootexperimenting.models.*
import com.example.springbootexperimenting.repos.TweetRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TweetService(private val tweetRepository: TweetRepository) {
    private val logger = LoggerFactory.getLogger(TweetService::class.java)

    @Transactional(readOnly = true)
    fun getTweets(): List<TweetResponse> {
        return tweetRepository
            .findAll()
            .map { convertToResponse(it) }
    }

    @Transactional(readOnly = true)
    fun getTweet(id: Long): TweetResponse {
        val tweet = tweetRepository
            .findById(id)
            .orElseThrow { NotFoundException("Tweet with id $id not found") }

        logger.info("Tweet found with id $id")

        return convertToResponse(tweet)
    }

    @Transactional
    fun createTweet(tweetRequest: TweetRequest): TweetResponse {
        val tweet = Tweet(message = tweetRequest.message)
        val savedTweet = tweetRepository.save(tweet)

        logger.info("Tweet created with id ${savedTweet.id}")

        return convertToResponse(savedTweet)
    }

    private fun convertToResponse(tweet: Tweet): TweetResponse = tweet.toResponse()
}