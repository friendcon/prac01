package com.conlogK.controller

import com.conlogK.controller.request.PostCreate
import com.conlogK.domain.Post
import com.conlogK.service.PostService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class PostController(
    private val postService: PostService
) {
    private val logger = LoggerFactory.getLogger(javaClass)


    @PostMapping("/post")
    fun post(
        @RequestBody @Valid request: PostCreate
    ) {
        val id = postService.write(request)
        // return mapOf<String, Long?>("postId" to id)
    }

    @GetMapping("/posts/{postId}")
    fun getPost(@PathVariable("postId") postId: Long): Post{
        val post = postService.getPost(postId)
        return post
    }
}