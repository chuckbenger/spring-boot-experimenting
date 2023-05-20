package com.example.springbootexperimenting.services

import com.example.springbootexperimenting.models.*
import com.example.springbootexperimenting.repos.TweetRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TweetService(private val tweetRepository: TweetRepository) {
    @Transactional(readOnly = true)
    fun getTweets(): List<TweetDTO> {
        return tweetRepository.findAll().map { it.toDTO() }
    }

    @Transactional(readOnly = true)
    fun getTweet(id: Long): TweetDTO {
        return tweetRepository.findById(id).orElseThrow().toDTO()
    }

    @Transactional
    fun createTweet(tweetDTO: TweetDTO): TweetDTO {
        return tweetRepository.save(tweetDTO.toEntity()).toDTO()
    }
}