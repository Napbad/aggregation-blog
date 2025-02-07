package org.napbad.clientbackend.controller

import lombok.extern.slf4j.Slf4j
import model.AuthorLoginResult
import org.babyfish.jimmer.client.meta.Api
import org.napbad.model.dto.author.*
import org.napbad.clientbackend.service.UserService
import org.napbad.expection.ErrorCode
import org.napbad.expection.ExceptionFactory
import org.napbad.clientbackend.utilities.MailUtil
import org.napbad.properties.JwtProperties
import org.napbad.utilities.log.logError
import org.napbad.utilities.log.logInfo
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.napbad.utilities.security.AccountUtil
import org.napbad.utilities.security.CaptchaGenerator
import org.napbad.utilities.security.JwtUtil
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.http.HttpStatus

@Api
@Slf4j
@RestController
@RequestMapping("user-account")
class UserAccountController(
    private val userService: UserService,
    private val redisTemplate: RedisTemplate<Any, Any>,
    private val mailUtil: MailUtil,
    private val jwtProperties: JwtProperties
) {
    @Api
    @PostMapping("/register")
    fun register(
        @RequestBody user: AuthorRegisterInput
    ): AuthorRegisterOutput {
        // 检查密码有效性
        if (!AccountUtil.checkPassword(user.password)) {
            logError("Invalid password provided during registration for user: ${user.email}")
            throw ExceptionFactory.exception(ErrorCode.PASSWORD_NOT_VALID, "Password is not valid")
        }

        // 检查邮箱有效性
        if (!AccountUtil.checkEmail(user.email)) {
            logError("Invalid email provided during registration: ${user.email}")
            throw ExceptionFactory.exception(ErrorCode.EMAIL_NOT_VALID, "Email is not valid")
        }

        // 检查用户名有效性
        if (!AccountUtil.checkName(user.authorName)) {
            logError("Invalid username provided during registration: ${user.authorName}")
            throw ExceptionFactory.exception(ErrorCode.USERNAME_NOT_VALID, "Username is not valid")
        }

        logInfo("Registering new user: ${user.email}")
        return userService.register(user)
    }

@Api
@PostMapping("/login")
fun login(
    @RequestBody user: AuthorLoginInput
): ResponseEntity<AuthorLoginResult> {

    try {
        var validAccountInput: Boolean = true

        // 邮箱，用户名只能输入一个
        if (user.email?.isBlank() == true && user.authorName?.isBlank() == true) {
            logError("Invalid email and username provided during login")
            throw ExceptionFactory.exception(ErrorCode.TOO_MUCH_ACCOUNT_INPUT, "Email and username cannot be input at the same time")
        }

        // 检查密码有效性
        if (user.password.isBlank() || !AccountUtil.checkPassword(user.password)) {
            logError("Invalid password provided during login for user: ${user.email}")
            throw ExceptionFactory.exception(ErrorCode.PASSWORD_NOT_VALID, "Password is not valid")
        }

        // 检查邮箱有效性
        if (user.email?.isBlank() == false && !AccountUtil.checkEmail(user.email)) {
            logError("Invalid email provided during login: ${user.email}")
            throw ExceptionFactory.exception(ErrorCode.EMAIL_NOT_VALID, "Email is not valid")
        }

        // 检查用户名有效性
        if (user.authorName?.isBlank() == false && !AccountUtil.checkName(user.authorName)) {
            logError("Invalid username provided during login: ${user.authorName}")
            throw ExceptionFactory.exception(ErrorCode.USERNAME_NOT_VALID, "Username is not valid")
        }

        logInfo("Logging in user: ${user.email}")
        val output = userService.login(user)

        val map = mapOf<String, Any>("id" to output.authorId)

        val createJWT = JwtUtil.createJWT(jwtProperties.jwtSecret, jwtProperties.jwtExpirationMs, map)

        val result = AuthorLoginResult(
            output,
            "Bearer $createJWT"
        )

        logInfo("Returning login result: (${result.authorLoginOutput})")

        return ResponseEntity.ok(result)
    } catch (e: Exception) {
        logError("Error during login: ${e.message}")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
    }
}

    @Api
    @PostMapping("/get-captcha")
    fun getCaptcha(
        @RequestBody user: AuthorVerifyInput
    ): String {
        // 检查邮箱有效性
        if (user.email.isBlank() || !AccountUtil.checkEmail(user.email)) {
            logError("Invalid email provided for captcha request: ${user.email}")
            throw ExceptionFactory.exception(ErrorCode.EMAIL_NOT_VALID, "Email is not valid")
        }


        val captcha = CaptchaGenerator.generateCaptcha()

        user.email.let {
            mailUtil.sendCaptcha(captcha, user.email)
            redisTemplate.opsForValue().set(it, captcha)
            logInfo("Captcha generated and stored for user: $it")
        }
        return "Sent successfully"
    }

    @Api
    @PostMapping("/logout")
    fun logout(): String {
        logInfo("User logged out")
        return "logout successfully"
    }

    @Api
    @PostMapping("/update")
    fun update(
        @RequestBody user: AuthorUpdateInput
    ): AuthorUpdateOutput {
        if (user.authorId == null) {
            logError("Attempted to update user without authorId")
            throw ExceptionFactory.exception(ErrorCode.USER_NOT_FOUND, "User not found")
        }

        logInfo("Updating user with authorId: ${user.authorId}")
        return userService.update(user)
    }
}
