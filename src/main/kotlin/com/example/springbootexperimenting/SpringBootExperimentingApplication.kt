package com.example.springbootexperimenting

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@AutoConfiguration
class SpringBootExperimentingApplication

fun main(args: Array<String>) {
    runApplication<SpringBootExperimentingApplication>(*args)
}
