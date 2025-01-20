package org.napbad.clientbackend

import org.babyfish.jimmer.client.EnableImplicitApi
import org.napbad.properties.AuthExcludePathProperties
import org.napbad.properties.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableImplicitApi
@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class, AuthExcludePathProperties::class)
@ConfigurationPropertiesScan("org.napbad.properties")
class AggregationBlogClientApplication

fun main(args: Array<String>) {
    runApplication<AggregationBlogClientApplication>(*args)
}
