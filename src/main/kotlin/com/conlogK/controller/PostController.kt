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
        /**
         * 데이터를 검증하는 이유 =>
         * 1. 클라이언트 개발자가 깜박할 수 있음 -> 실수로 값을 안보낼 수 있음
         * 2. 클라이언트 버그로 값이 누락될 수 있음
         * 3. 보안 공격
         * 4. DB 저장시 의도치 않은 오류 발생
         */
        logger.info("params ${params.toString()}")
        /*var title = params.title
        if(title == null || title.equals("")) {*/
            // error
            /**
             * 1. 하나하나 체크하는건.. 비효율적..
             * 2. 반복작업이 많을시에 의심해라
             * 3. 누락될 염려
             * 4. 검증해야 할 값이 정말 많다
             */
        /*    throw Exception("title is null")
        }*/
        /*var content = params.content
        if(content == null || content.equals("")) {
            // error
        }*/
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
    /*@PostMapping("/post")
    fun post(
        @RequestParam Map<String, String> params
    ): String {
        logger.info("hello")
        return "hello";
    }*/
}