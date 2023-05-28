package com.example.springbootexperimenting.service

import com.example.springbootexperimenting.controller.NotFoundException
import com.example.springbootexperimenting.entities.Tweet
import com.example.springbootexperimenting.entities.User
import com.example.springbootexperimenting.dto.TweetRequest
import com.example.springbootexperimenting.repository.TweetRepository
import com.example.springbootexperimenting.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.time.LocalDateTime


@SpringBootTest
class TweetServiceTest {

    @Autowired
    private lateinit var tweetService: TweetService

    @MockBean
    private lateinit var tweetRepository: TweetRepository

    @MockBean
    private lateinit var userRepository: UserRepository

    @MockBean
    private lateinit var userService: UserService

    private val testUser = User(id = 1, username = "user", password = "password", email = "test@test.com")

    @Test
    fun `getTweets should return a list of tweets`() {
        Mockito.`when`(tweetRepository.findAll()).thenReturn(
            listOf(
                Tweet(id = 1, message = "Hello, world!", createdAt = LocalDateTime.now(), user = testUser),
                Tweet(id = 2, message = "Hello, world Again", createdAt = LocalDateTime.now(), user = testUser),
            )
        )

        val tweets = tweetService.getTweets()

        assertThat(tweets).hasSize(2)
        assertThat(tweets[0].message).isEqualTo("Hello, world!")
        assertThat(tweets[1].message).isEqualTo("Hello, world Again")
    }

    @Test
    fun `getTweet should return a tweet`() {
        Mockito.`when`(tweetRepository.findById(1)).thenReturn(
            java.util.Optional.of(
                Tweet(id = 1, message = "Hello, world!", createdAt = LocalDateTime.now(), user = testUser)
            )
        )

        val tweet = tweetService.getTweet(1)

        assertThat(tweet.message).isEqualTo("Hello, world!")
    }

    @Test
    fun `getTweet should throw error when not existing`() {
        Mockito.`when`(tweetRepository.findById(1)).thenReturn(
            java.util.Optional.empty()
        )

        assertThatThrownBy { tweetService.getTweet(1) }
            .isInstanceOf(NotFoundException::class.java)
            .hasMessageContaining("Tweet with id 1 not found")
    }

    @Test
    fun `createTweet should return a tweet`() {
        Mockito.`when`(tweetRepository.save(any())).thenReturn(
            Tweet(id = 1, message = "Hello, world!", createdAt = LocalDateTime.now(), user = testUser),
        )
        Mockito.`when`(userService.getCurrentUser()).thenReturn(testUser)

        val tweet = tweetService.createTweet(TweetRequest(message = "Hello, world!"))

        assertThat(tweet.message).isEqualTo("Hello, world!")
    }
}