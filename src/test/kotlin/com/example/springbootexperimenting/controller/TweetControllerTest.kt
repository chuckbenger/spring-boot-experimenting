package com.example.springbootexperimenting.controller

import com.example.springbootexperimenting.dto.TweetResponse
import com.example.springbootexperimenting.service.TweetService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.anyOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
class TweetControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var tweetService: TweetService

    @Test
    fun `gets tweets`() {
        val tweetResponses = listOf(
            TweetResponse(
                id = 1,
                message = "Hello, world!",
                upVotes = 0,
                downVotes = 0,
                createdAt = LocalDateTime.now()
            )
        )
        Mockito.`when`(tweetService.getTweets()).thenReturn(tweetResponses)

        mvc.perform(get("/api/tweets"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].message").value("Hello, world!"))
    }

    @Test
    fun `gets tweet`() {
        val tweetResponse = TweetResponse(
            id = 1,
            message = "Hello, world!",
            upVotes = 0,
            downVotes = 0,
            createdAt = LocalDateTime.now()
        )
        Mockito.`when`(tweetService.getTweet(1)).thenReturn(tweetResponse)

        mvc.perform(get("/api/tweets/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Hello, world!"))
    }

    @Test
    fun `gets tweet should throw error when not existing`() {
        Mockito.`when`(tweetService.getTweet(1)).thenThrow(NotFoundException("Tweet not found"))

        mvc.perform(get("/api/tweets/1"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value("Tweet not found"))
    }

    @WithMockUser
    @Test
    fun `create tweet when passing valid payload`() {
        val tweetResponse = TweetResponse(
            id = 1,
            message = "Hello, world!",
            upVotes = 0,
            downVotes = 0,
            createdAt = LocalDateTime.now()
        )
        Mockito.`when`(tweetService.createTweet(anyOrNull())).thenReturn(tweetResponse)

        mvc.perform(
            post("/api/tweets")
                .contentType("application/json")
                .content("{\"message\": \"Hello, world!\"}")
        )
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.message").value("Hello, world!"))
    }

    @WithMockUser
    @Test
    fun `create tweet when passing null message`() {
        mvc.perform(
            post("/api/tweets")
                .contentType("application/json")
                .content("{\"message\": null}")
        )
            .andExpect(status().isBadRequest())
    }

    @WithMockUser
    @Test
    fun `create tweet when passing blank message`() {
        mvc.perform(
            post("/api/tweets")
                .contentType("application/json")
                .content("{\"message\": \"\"}")
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors.message").value("size must be between 1 and 255"))
    }

    @WithMockUser
    @Test
    fun `create tweet when passing too long message`() {
        val message = "a".repeat(256)
        mvc.perform(
            post("/api/tweets")
                .contentType("application/json")
                .content("{\"message\": \"$message\"}")
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors.message").value("size must be between 1 and 255"))
    }
}