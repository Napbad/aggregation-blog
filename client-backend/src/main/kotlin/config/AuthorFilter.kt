package org.napbad.clientbackend.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.slf4j.Slf4j
import org.napbad.expection.ErrorCode
import org.napbad.expection.ExceptionFactory
import org.napbad.properties.AuthExcludePathProperties
import org.napbad.properties.JwtProperties
import org.napbad.utilities.UserContext
import org.napbad.utilities.log.logError
import org.napbad.utilities.log.logInfo
import org.napbad.utilities.security.JwtUtil
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter

@Slf4j
@Component
class AuthorFilter(
    private val jwtProperties: JwtProperties,
    private val authExcludePathProperties: AuthExcludePathProperties,
    private val antPathMatcher: AntPathMatcher
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        // print request basic info
        logInfo("Request Method: ${request.method}")
        logInfo("Request URI: ${request.requestURI}")
        logInfo("Request Headers:")
        request.headerNames?.asSequence()?.forEach { headerName ->
            logInfo("  $headerName: ${request.getHeader(headerName)}")
        }

        if (isExcluded(request.requestURI)) {
            filterChain.doFilter(request, response)
            return
        }

        var token: String? = request.getHeader("Authorization")
            ?: throw ExceptionFactory.exception(ErrorCode.USER_NOT_LOGIN, "token is null")
        token = token?.replace("Bearer ", "")
        logInfo("token: $token")

        try {
            val claims = JwtUtil.parseJWT(jwtProperties.jwtSecret, token)
            if (claims?.isEmpty() == true) {
                throw ExceptionFactory.exception(ErrorCode.USER_NOT_LOGIN, "token is invalid")
            }

            UserContext.userId = claims?.get("id") as Int

            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            logError("error: ${e.message}")
        }
    }

    private fun isExcluded(path: String): Boolean {
        return authExcludePathProperties
            .excludePaths
            ?.stream()
            ?.anyMatch { excludePath -> antPathMatcher.match(excludePath, path) } ?: false
    }
}