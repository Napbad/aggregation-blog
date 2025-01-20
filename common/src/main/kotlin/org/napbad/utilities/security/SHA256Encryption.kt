package org.napbad.utilities.security

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/*
* Author: Napbad
* Version: 1.0
*/
object SHA256Encryption {
    private val sb = StringBuffer("HACOJ")

    fun getSHA256(input: String): String {
        try {
            // 获取MessageDigest实例，并指定SHA-256算法
            val digest = MessageDigest.getInstance("SHA-256")

            // 计算哈希值
            val hash = digest.digest((input + sb.toString()).toByteArray(StandardCharsets.UTF_8))

            // 将字节数组转换为十六进制字符串
            val hexString = StringBuilder()
            for (b in hash) {
                val hex = Integer.toHexString(0xff and b.toInt())
                if (hex.length == 1) hexString.append('0')
                hexString.append(hex)
            }

            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }
}