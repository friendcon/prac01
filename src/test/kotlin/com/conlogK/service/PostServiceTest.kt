package com.conlogK.service

import com.conlogK.controller.request.PostCreate
import com.conlogK.repository.PostRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostServiceTest(
    @Autowired val postService: PostService,
    @Autowired val postRepository: PostRepository
) {

    @BeforeEach
    fun test() {
        postRepository.deleteAll()
    }
    @Test
    @DisplayName("글 작성")
    fun postCreateTest() {

        // given
        val postCreate = PostCreate(
            title = "제목이다",
            content = "내용이다"
        )

        // when
        postService.write(postCreate)

        // then
        assertEquals(1L, postRepository.count())
        val post = postRepository.findAll().get(0)
        assertEquals("제목이다", post.title)
        assertEquals("내용이다", post.content)
    }

    @Test
    @DisplayName("글 1개 조회")
    fun postReadTest() {
        // given
        val postCreate = PostCreate(
            title = "123456789012345",
            content = "본문이여요"
        )

        postService.write(postCreate)

        val postId = 1L

        //when
        val post = postService.getPost(1L)

        // then
        assertNotNull(post)
        assertEquals(1L, postRepository.count())
        assertEquals("1234567890", post.title)
        assertEquals("본문이여요", post.content)
    }
}