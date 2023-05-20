package com.example.springbootexperimenting

import com.example.springbootexperimenting.models.TweetDTO
import com.example.springbootexperimenting.services.TweetService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tweets")
class TweetController(private val tweetService: TweetService) {

    @GetMapping("/")
    fun getTweets(): ResponseEntity<*> {
        return ResponseEntity.ok(tweetService.getTweets())
    }

    @GetMapping("/{id}")
    fun getTweet(@PathVariable id: Long): ResponseEntity<*> {
        return ResponseEntity.ok(tweetService.getTweet(id))
    }

    @PostMapping("/")
    fun createTweet(@RequestBody tweetDTO: TweetDTO): ResponseEntity<*> {
        return ResponseEntity
            .status(201)
            .body(tweetService.createTweet(tweetDTO))
    }
}