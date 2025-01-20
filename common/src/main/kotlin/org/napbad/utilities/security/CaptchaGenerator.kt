package org.napbad.utilities.security

import java.util.*

/*
* Author: Napbad
* Version: 1.0
*/

object CaptchaGenerator {
    private const val LENGTH = 6
    private val CHARACTERS = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    private val RANDOM = Random()

    /**
     * 生成一个指定位位数字的验证码。
     * @return 生成的指定位位数字验证码字符串。
     */
    /**
     * 生成一个六位数字的验证码。
     * @return 生成的六位数字验证码字符串。
     */
    @JvmOverloads
    fun generateCaptcha(length: Int = LENGTH): String {
        val captcha = CharArray(length)
        for (i in 0..<length) {
            captcha[i] = CHARACTERS[RANDOM.nextInt(CHARACTERS.size)]
        }
        return String(captcha)
    }
}
