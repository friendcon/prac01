package com.conlogK.controller

import com.conlogK.controller.response.CommonResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionController {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseBody
    fun invalidRequestHandler(e: MethodArgumentNotValidException): CommonResponse {
        val response = CommonResponse("200", "잘못된 요청")

        for(field in e.fieldErrors) {
            response.addValidation(field.field, field.defaultMessage!!)
        }
        return response
    }

    fun CommonResponse.addValidation(field: String, message: String): CommonResponse {
        validation.put(field, message)
        return this
    }
}