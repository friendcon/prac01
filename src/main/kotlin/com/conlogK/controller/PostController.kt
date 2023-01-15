package com.conlogK.controller

import com.conlogK.controller.request.PostCreate
import com.conlogK.service.PostService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class PostController(
    private val postService: PostService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/posts")
    fun get(): String {
        return "hello";
    }

    @PostMapping("/post")
    fun post(
        @RequestBody @Valid request: PostCreate
    ) {
        val id = postService.write(request)
        // return mapOf<String, Long?>("postId" to id)
    }
}