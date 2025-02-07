package org.napbad.clientbackend.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "ab.file.save")
class FileSaveProperties {
    val uploadDir: String = "~/ab/file"
}