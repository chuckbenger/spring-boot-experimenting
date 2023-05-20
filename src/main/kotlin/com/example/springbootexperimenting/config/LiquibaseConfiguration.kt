package com.example.springbootexperimenting.config

import liquibase.integration.spring.SpringLiquibase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class LiquibaseConfiguration(private val dataSource: DataSource) {
    @Bean
    fun liquibase(): SpringLiquibase {
        val liquibase = SpringLiquibase()
        liquibase.dataSource = dataSource
        liquibase.changeLog = "classpath:/db/changelog/changelog-master.yaml"
        liquibase.contexts = "development,test,production"
        liquibase.defaultSchema = "public"
        liquibase.isDropFirst = false
        return liquibase
    }
}