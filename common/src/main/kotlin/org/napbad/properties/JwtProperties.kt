package org.napbad.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "ab.jwt")
class JwtProperties (
    var jwtSecret: String = "napbad",
    var jwtExpirationMs: Long = 86400000,
    var jwtTokenName: String = "Authorization"
){
//    var jwtSecret: String = "napbad"
//        get() = field.ifBlank { "napbad" }
//        set(value) {
//            field = value.ifBlank { "napbad" }
//        }
//    var jwtExpirationMs: Long = 86400000
//        get() = if (field == 0L) 86400000 else field
//        set(value) {
//            field = if (value == 0L) 86400000 else value
//        }
//    var jwtTokenName: String = "Authorization"
//        get() = field.ifBlank { "Authorization" }
//        set(value) {
//            field = value.ifBlank { "Authorization" }
//        }
}