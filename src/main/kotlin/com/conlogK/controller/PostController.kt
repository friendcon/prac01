package com.conlogK.controller

import com.conlogK.controller.request.PostCreate
import org.slf4j.LoggerFactory
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 *  RestController = ResponseBody + Controller
 */
@RestController
class PostController(
) {
    /**
     * Kotlin 에서 로그 사용방법
     */
    private val logger = LoggerFactory.getLogger(javaClass)
    /**
        데이터 기반 응답을 생성하려고 함
        SSR -> jsp, thymeleaf, mustache, freemarker -> html rendering
        SPA -> vue, react, nuxt.jx(vue+SSR), next.js(react+SSR) -> javascript + API (JSON)
    */
    /**
     *  HTTP Method
     *  GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
     *  글 등록
     *  POST METHOD
     */
    @GetMapping("/posts")
    fun get(): String {
        return "hello";
    }

    @PostMapping("/post")
    fun post(
        @RequestBody @Valid params: PostCreate,
        bindingResult: BindingResult
    ): Map<String, String> {
        logger.info("params ${params.toString()}")
        // 입력값 검증 모든 API 마다 반복적으로 들어가야해서 해결해야함
        if(bindingResult.hasErrors()) {
            val fieldErrors = bindingResult.fieldErrors
            val firstFieldError = fieldErrors[0]
            val fieldName = firstFieldError.field// title

            /**
             * defaultMessage
             */
            val errorMessage = firstFieldError.defaultMessage // error message
            var map= HashMap<String, String>()
            map[fieldName] = errorMessage!!
            return map
        }
        return mapOf()
    }
}