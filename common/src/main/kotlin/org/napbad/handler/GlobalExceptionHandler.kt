package org.napbad.handler

/*
* Author: Napbad
* Version: 1.0
*/

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {



    @ExceptionHandler(value = [Exception::class])
    fun handleException(ex: Exception): ResponseEntity<String> {
        ex.printStackTrace()
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.toString())
    }
}
