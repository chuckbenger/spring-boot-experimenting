package com.example.springbootexperimenting.repos

import com.example.springbootexperimenting.config.LiquibaseConfiguration
import com.example.springbootexperimenting.entities.Tweet
import com.example.springbootexperimenting.entities.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.context.annotation.Import

@DataJpaTest
@AutoConfigureTestDatabase
@Import(LiquibaseConfiguration::class)
class TweetRepositoryTest {

    @Autowired
    private lateinit var tweetRepository: TweetRepository

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Test
    fun `can create and find book`() {
        val user = entityManager.persist(User(username = "user", password = "password", email = "test@test.com"))
        entityManager.persist(Tweet(message = "message", user = user))

        val tweet = tweetRepository.findById(1L).get()

        assertThat(tweet.message).isEqualTo("message")
        assertThat(tweet.upVotes).isEqualTo(0)
        assertThat(tweet.downVotes).isEqualTo(0)
        assertThat(tweet.createdAt).isNotNull()
        assertThat(tweet.updatedAt).isNotNull()
    }
}