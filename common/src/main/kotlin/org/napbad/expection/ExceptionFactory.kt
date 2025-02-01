package org.napbad.expection

object ExceptionFactory {
    fun exception(code: ErrorCode): BaseException {
        return when (code) {
            ErrorCode.INPUT_INVALID -> BaseException(code, "Input invalid")
            ErrorCode.USER_NOT_FOUND -> BaseException(code, "User not found")
            ErrorCode.USER_ALREADY_EXIST -> BaseException(code, "User already exists")
            ErrorCode.USER_NOT_VERIFIED -> BaseException(code, "User not verified")
            ErrorCode.USER_NOT_LOGIN -> BaseException(code, "User not logged in")
            ErrorCode.USER_NOT_AUTHORIZED -> BaseException(code, "User not authorized")
            ErrorCode.USER_NOT_ADMIN -> BaseException(code, "User is not an admin")
            ErrorCode.UNKNOWN_ERROR -> BaseException(code, "Unknown error")
            ErrorCode.USERNAME_NOT_VALID -> BaseException(code, "Username is not valid")
            ErrorCode.PASSWORD_NOT_VALID -> BaseException(code, "Password is not valid")
            ErrorCode.EMAIL_NOT_VALID -> BaseException(code, "Email is not valid")
            ErrorCode.USERNAME_OR_PASSWORD_WRONG -> BaseException(code, "Username or password is wrong")
            ErrorCode.TOO_MUCH_ACCOUNT_INPUT -> BaseException(code, "Too much account input")
        }
    }

    fun exception(code: ErrorCode, message: String): BaseException {
        val exception = exception(code)
        exception.message = message

        return exception
    }
}