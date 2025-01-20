package org.napbad.expection

class BaseException(
    code: ErrorCode, message: String
) : Exception(message) {
    private val code: ErrorCode = ErrorCode.UNKNOWN_ERROR
    override var message: String = ""

    override fun toString(): String {
        return "[error] code: $code, message: $message"
    }
}

enum class ErrorCode {
    INPUT_INVALID,
    USERNAME_NOT_VALID,
    PASSWORD_NOT_VALID,
    EMAIL_NOT_VALID,
    USER_NOT_FOUND,
    USER_ALREADY_EXIST,
    USER_NOT_VERIFIED,
    USER_NOT_LOGIN,
    USER_NOT_AUTHORIZED,
    USER_NOT_ADMIN,
    UNKNOWN_ERROR,
    USERNAME_OR_PASSWORD_WRONG
}