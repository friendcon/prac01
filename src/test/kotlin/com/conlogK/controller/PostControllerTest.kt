package com.conlogK.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest // MockMvc 주입
class PostControllerTest(
    @Autowired val mockMvc: MockMvc
) {
    @Test
    @DisplayName("/posts 요청시 Hello 출력")
    fun test() {

        // Expected
        mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("hello"))
            .andDo(MockMvcResultHandlers.print()) // contents 요약
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
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"title\" : \"제목입니다\", \"content\" : \"내용입니다.\"}"
                )
            )
            .andExpect(status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @DisplayName("/post 요청시 title 값은 필수다")
    fun test3() {
        mockMvc.perform(MockMvcRequestBuilders.post("/post")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                "{\"title\" : null , \"content\" : \"내용입니다.\"}"
            )
        )
            .andExpect(status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
    }
}