package org.napbad.utilities.log

import java.util.logging.Level
import java.util.logging.Logger

val logger: Logger = Logger.getLogger("main")

fun logInfo(message: String) {
    logger.info(message)
}

fun logWarn(message: String) {
    logger.log(Level.WARNING, message)
}

fun logError(message: String, throwable: Throwable? = null) {
    if (throwable == null) {
        logger.log(Level.SEVERE, message)
    } else {
        logger.log(Level.SEVERE, message, throwable)
    }
}

fun debug(message: String) {
    logger.log(Level.FINE, message)
}
