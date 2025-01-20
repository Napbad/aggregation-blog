package org.napbad.utilities.security

import org.napbad.model.entity.Author
import java.util.regex.Pattern

/*
* @Author：Napbad
* @Version：1.0
* 账户相关验证工具类。
*/
object AccountUtil {
    // 用于名字格式校验的正则表达式模式
    // 名字可能由字母（包括大小写）、数字、下划线、连字符和中文字符组成，且可以包含空格，长度在4 - 20之间
    private const val NAME_REGEX = "^[_\\u4e00-\\u9fa5a-zA-Z0-9]{2,20}$"
    private val NAME_PATTERN: Pattern = Pattern.compile(NAME_REGEX, Pattern.UNICODE_CHARACTER_CLASS)

    // 加强密码强度要求的正则表达式模式
    // 要求必须包含至少一个数字、一个字母，长度在8到20个字符之间
    private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d).{8,20}$"
    private val PASSWORD_PATTERN: Pattern = Pattern.compile(PASSWORD_REGEX)

    //
    private const val PHONE_REGEX = "^1[3-9]\\d{9}$"
    private val PHONE_PATTERN: Pattern = Pattern.compile(PHONE_REGEX)

    // 电子邮件格式校验的正则表达式模式
    private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    private val EMAIL_PATTERN: Pattern = Pattern.compile(EMAIL_REGEX)

    /**
     * 验证提供的密码是否符合要求。
     *
     * @param password 待验证的密码字符串。
     * @return 如果密码有效则返回true，否则返回false。
     */
    fun checkPassword(password: String?): Boolean {
        if (password == null) {
            return false
        }
        // 检查密码不为空，长度至少8位，最多20位，并且包含至少一个数字、一个字母
        return PASSWORD_PATTERN.matcher(password).find()
    }

    /**
     * 验证提供的电话号码是否有效。
     *
     * @param phone 待验证的电话号码字符串。
     * @return 如果电话号码有效则返回true，否则返回false。
     */
    fun checkPhone(phone: String?): Boolean {
        if (phone == null) {
            return false
        }
        // 检查电话号码不为空且恰好11位。
        return PHONE_PATTERN.matcher(phone).find()
    }

    /**
     * 验证提供的电子邮件是否有效。
     *
     * @param email 待验证的电子邮件字符串。
     * @return 如果电子邮件有效则返回true，否则返回false。
     */
    fun checkEmail(email: String?): Boolean {
        if (email == null) {
            return false
        }
        // 检查电子邮件不为空，包含至少一个"@"字符，并且"@"不在开头或结尾，且符合电子邮件的一般格式
        return EMAIL_PATTERN.matcher(email).find()
    }

    /**
     * 验证提供的名字是否有效。
     *
     * @param name 待验证的名字字符串。
     * @return 如果名字有效则返回true，否则返回false。
     */
    fun checkName(name: String?): Boolean {
        if (name == null) {
            return false
        }
        return NAME_PATTERN.matcher(name).find()
    }

    fun checkAuthorAccountEqual(
        author1: Author,
        author2: Author
    ): Boolean {

        return true
    }
}
