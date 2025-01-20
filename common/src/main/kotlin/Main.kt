package org.napbad

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
class Common

fun main(args: Array<String>) {
    runApplication<Common>(*args)
}
