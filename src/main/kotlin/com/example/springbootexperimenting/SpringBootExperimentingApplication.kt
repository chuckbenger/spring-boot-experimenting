package com.example.springbootexperimenting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class SpringBootExperimentingApplication

fun main(args: Array<String>) {
    runApplication<SpringBootExperimentingApplication>(*args)
}
