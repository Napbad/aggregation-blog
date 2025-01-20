package org.napbad.clientbackend.controller

import lombok.extern.slf4j.Slf4j
import org.babyfish.jimmer.client.EnableImplicitApi
import org.babyfish.jimmer.client.meta.Api
import org.napbad.model.dto.author.*
import org.napbad.model.entity.Author
import org.napbad.clientbackend.service.UserService
import org.napbad.expection.ErrorCode
import org.napbad.expection.ExceptionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.napbad.utilities.security.AccountUtil
import org.napbad.utilities.security.CaptchaGenerator


@Api
@Slf4j
@CrossOrigin
@RestController("user-account")
class UserAccountController(
    private val userService: UserService,
    private val redisTemplate: RedisTemplate<Any, Any>,
    private val template: RedisTemplate<Any, Any>
) {
    @Api
    @PostMapping("/register")
    fun register(
        @RequestBody user: AuthorRegisterInput
    ): AuthorRegisterOutput {

        if (AccountUtil.checkPassword(user.password)) {
            throw ExceptionFactory.exception(ErrorCode.PASSWORD_NOT_VALID, "Password is not valid")
        }

        if (!AccountUtil.checkEmail(user.email)) {
            throw ExceptionFactory.exception(ErrorCode.EMAIL_NOT_VALID, "Email is not valid")
        }

        if (!AccountUtil.checkName(user.authorName)) {
            throw ExceptionFactory.exception(ErrorCode.USERNAME_NOT_VALID, "Username is not valid")
        }

        return userService.register(user)
    }

    @Api
    @PostMapping("/login")
    fun login(
        @RequestBody user: AuthorLoginInput
    ): AuthorLoginOutput {

        if ((if (user.password?.isBlank() == true) { !AccountUtil.checkPassword(user.password) } else { true } )) {
            throw ExceptionFactory.exception(ErrorCode.PASSWORD_NOT_VALID, "Password is not valid")
        }
        if (!AccountUtil.checkEmail(user.email)) {
            throw ExceptionFactory.exception(ErrorCode.EMAIL_NOT_VALID, "Email is not valid")
        }

        if (!AccountUtil.checkName(user.authorName)) {
            throw ExceptionFactory.exception(ErrorCode.USERNAME_NOT_VALID, "Username is not valid")
        }

        return userService.login(user)
    }


    @Api
    @PostMapping("/get-captcha")
    fun getCaptcha(
        @RequestBody user: AuthorVerifyInput
    ): String {

        if (user.email?.isBlank() ?: !AccountUtil.checkEmail(user.email)) {
            throw ExceptionFactory.exception(ErrorCode.EMAIL_NOT_VALID, "Email is not valid")
        }

        val captcha = CaptchaGenerator.generateCaptcha()
        user.email?.let { redisTemplate.opsForValue().set(it, captcha) };
        return "Sent successfully"
    }

    @Api
    @PostMapping("/logout")
    fun logout(): String {
        return "logout successfully"
    }

    @Api
    @PostMapping("/update")
    fun update(
        @RequestBody user: AuthorUpdateInput
    ): AuthorUpdateOutput {
        if(user.authorId == null) {
            throw ExceptionFactory.exception(ErrorCode.USER_NOT_FOUND, "User not found")
        }

        return userService.update(user)
    }

}