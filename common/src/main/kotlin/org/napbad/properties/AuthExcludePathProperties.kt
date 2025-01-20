package org.napbad.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/*
* Author: Napbad
* Version: 1.0
*/

@Component
@ConfigurationProperties(prefix = "ab.auth.exclude")
class AuthExcludePathProperties {
    var excludePaths: List<String>? = null
}
