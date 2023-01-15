package com.conlogK.controller

import com.conlogK.controller.request.PostCreate
import com.conlogK.domain.Post
import com.conlogK.repository.PostRepository
import com.conlogK.service.PostService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

//@WebMvcTest // MockMvc 주입
@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val postService: PostService,
    @Autowired val postRepository: PostRepository,
    @Autowired val objectMapper: ObjectMapper
) {
    @BeforeEach
    fun clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/post 요청시 게시글 작성")
    fun postTest() {
        // 글 제목
        // 글 내용

        // content-type : application/json 으로 보냄
        /*mockMvc.perform(MockMvcRequestBuilders.post("/post")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "글 제목이여요")
                .param("content", "글 내용이여요")
            )
            .andExpect(status().isOk)
            .andDo(MockMvcResultHandlers.print())*/
        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                .contentType(APPLICATION_JSON)
                .content(
                    "{\"title\" : \"제목입니다\", \"content\" : \"내용입니다.\"}"
                )
            )
            .andExpect(status().isOk)
            .andDo(print())
    }

    @Test
    @DisplayName("/post 요청시 title 값은 필수다")
    fun test3() {
        mockMvc.perform(MockMvcRequestBuilders.post("/post")
            .contentType(APPLICATION_JSON)
            .content(
                "{\"title\" : null , \"content\" : \"내용입니다.\"}"
            )
        )
            .andExpect(status().isBadRequest)
            .andDo(print())
    }

    /**
     * 각각의 테스트가 다른 테스트에 영향을 주지 않도록 테스트 코드를 작성하는 것이 중요하다
     */
    @Test
    @DisplayName("/posts 요청시 DB에 값 저장")
    fun test4() {
        // given
        val request = PostCreate(
            title = "제목이여요",
            content = "내용이여요"
        )
        val objectMapper = ObjectMapper() // ObjectMapper 알아보기
        val json = objectMapper.writeValueAsString(request)

        println(json)

        // expected
        mockMvc.perform(MockMvcRequestBuilders.post("/post")
            .contentType(APPLICATION_JSON) // option + enter
            .content(
                json
            )
        )
            .andExpect(status().isOk)
            .andDo(print())

        Assertions.assertEquals(1L, postRepository.count())

        val post = postRepository.findAll().get(0)
        assertEquals("제목이여요",post.title)
        assertEquals("내용이여요",post.content)
    }

    @Test
    @DisplayName("게시글 한개 조회")
    fun test05() {
        // given
        val postCreate = Post(
            title = "123456789012345",
            content = "글 내용이여요"
        )

        /**
         * 클라이언트 요청 : 글 제목 10글자만 보내줘
         */
        val response = postRepository.save(postCreate)

        val boardId = 1L

        // when + then
        mockMvc.perform(MockMvcRequestBuilders.get("/posts/${boardId}")
            .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(boardId))
            .andExpect(jsonPath("$.title").value("1234567890"))
            .andExpect(jsonPath("$.content").value(postCreate.content))
            .andDo(print())

        // expected
    }
}