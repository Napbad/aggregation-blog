package org.napbad.clientbackend.utilities

import jakarta.mail.MessagingException
import lombok.extern.slf4j.Slf4j
import org.napbad.utilities.log.logError
import org.napbad.utilities.log.logInfo
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import java.io.UnsupportedEncodingException

/*
* Author: Napbad
* Version: 1.0
*/

/**
 * 邮件工具类，用于发送邮件。
 */
@Slf4j
@Component
class MailUtil
/**
 * 构造函数，注入JavaMailSender。
 *
 * @param javaMailSender 用于发送邮件的JavaMailSender实例
 */(// JavaMailSender接口实例，用于发送邮件
    val javaMailSender: JavaMailSender
) {
    fun sendCaptcha(captcha: String, to: String) {
        sendMail(to, "验证码", "您的验证码是：<br> <strong>$captcha<strong> <br>请勿泄露。", true)
    }

    /**
     * 发送邮件，可以选择正文是否为HTML格式。
     *
     * @param to 收件人邮箱
     * @param subject 邮件主题
     * @param content 邮件正文
     * @param isHtml 是否将正文作为HTML发送
     */
    /**
     * 发送简单邮件，正文默认为纯文本。
     *
     * @param to 收件人邮箱
     * @param subject 邮件主题
     * @param content 邮件正文
     */
    @JvmOverloads
    fun sendMail(to: String, subject: String, content: String, isHtml: Boolean = false) {
        // 创建一个邮件消息
        val message = javaMailSender.createMimeMessage()

        // 创建 MimeMessageHelper，用于简化邮件内容设置
        var helper: MimeMessageHelper? = null
        try {
            helper = MimeMessageHelper(message, false)

            // 尝试发送邮件，确保发送成功
            // 设置发件人、收件人、主题和正文
            helper.setFrom(FROM, FROM_NAME)
            helper.setTo(to)
            helper.setSubject(subject)
            helper.setText(content, isHtml)

            // 发送邮件
            javaMailSender.send(message)

            // 记录成功发送的日志
            logInfo("success send to: ${to}")
        } catch (e: MessagingException) {
            logError("send mail exception \n ${e.message}")
            // 发送邮件异常，抛出运行时异常
        } catch (e: UnsupportedEncodingException) {
            logError("unsupported encoding \n ${e.message}")
        }
    }

    companion object {
        const val FROM: String = "sjs1910191@163.com"
        const val FROM_NAME: String = "Blog"
    }
}
