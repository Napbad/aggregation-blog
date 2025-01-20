package org.napbad.clientbackend.config

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher

@Component
class AntPathMatcherConfig {
    @Bean
    fun antPathMatcher(): AntPathMatcher {
        return AntPathMatcher()
    }
}