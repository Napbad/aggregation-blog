package org.napbad.utilities.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.JwtParserBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureAlgorithm
import java.nio.charset.StandardCharsets
import java.util.*

/*
* Author: Napbad
* Version: 1.0
*/
/**
 * JWT工具类，提供JWT的创建和解析功能。
 */
object JwtUtil {
    /**
     * 创建JWT Token。
     *
     * @param secretKey JWT的秘钥，用于签名和验证。
     * @param ttlMillis JWT的有效期时间（毫秒）。
     * @param claims 要设置到JWT中的声明信息。
     * @return 生成的JWT Token字符串。
     */
    fun createJWT(secretKey: String, ttlMillis: Long, claims: Map<String?, Any?>?): String {

        // 计算JWT的过期时间
        val expMillis = System.currentTimeMillis() + ttlMillis
        val exp = Date(expMillis)
        val key = Keys.hmacShaKeyFor(secretKey.toByteArray())

        // 构建JWT
        val builder: JwtBuilder = Jwts.builder()
            .claims(claims)
            .expiration(Date(ttlMillis))
            .signWith(key)
        return builder.compact()
    }

    /**
     * 解析JWT Token。
     *
     * @param secretKey JWT的秘钥，用于签名验证。
     * @param token 要解析的JWT Token字符串。
     * @return 解析后的JWT声明信息。
     */
    fun parseJWT(secretKey: String, token: String?): Claims? {
        // 解析JWT
        return Jwts.parser()
            .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
            .build()
            .parseEncryptedClaims(token)
            .payload
    }
}
