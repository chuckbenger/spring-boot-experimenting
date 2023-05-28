package com.example.springbootexperimenting.repository

import com.example.springbootexperimenting.entities.Tweet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TweetRepository : JpaRepository<Tweet, Long>