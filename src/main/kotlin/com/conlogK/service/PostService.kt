package com.conlogK.service

import com.conlogK.controller.request.PostCreate
import com.conlogK.domain.Post
import com.conlogK.repository.PostRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class PostService(
    @Autowired
    val postRepository: PostRepository
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun write(postCreate: PostCreate) {
        // 1. 저장한 Entity 를 리턴
        // 2. 저장한 Entity 의 id 를 리턴
        // 3. 응답 필요 없음
        // Bad Case : 서버에서 fix 하는 경우..ㅎㅎ..
        val post = Post(
            title = postCreate.title,
            content = postCreate.content
        )
        postRepository.save(post)
    }
}