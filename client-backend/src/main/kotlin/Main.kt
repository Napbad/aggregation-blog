package org.napbad.clientbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AggregationBlogApplication

fun main(args: Array<String>) {
    runApplication<AggregationBlogApplication>(*args)
}
