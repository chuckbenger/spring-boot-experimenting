package com.example.springbootexperimenting.controller

import com.example.springbootexperimenting.dto.TweetRequest
import com.example.springbootexperimenting.dto.TweetResponse
import com.example.springbootexperimenting.service.TweetService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tweets")
class TweetController(private val tweetService: TweetService) {

    @GetMapping
    fun getTweets(): ResponseEntity<List<TweetResponse>> {
        val tweets = tweetService.getTweets()
        return ResponseEntity.ok(tweets)
    }

    @GetMapping("{id}")
    fun getTweet(@PathVariable id: Long): ResponseEntity<TweetResponse> {
        val tweet = tweetService.getTweet(id)
        return ResponseEntity.ok(tweet)
    }

    @PostMapping
    fun createTweet(@Valid @RequestBody tweetRequest: TweetRequest): ResponseEntity<TweetResponse> {
        val tweet = tweetService.createTweet(tweetRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(tweet)
    }
}