package com.conlogK.controller.request

import javax.validation.constraints.NotBlank

data class PostCreate(
    @field:NotBlank(message = "제목을 입력해주세요")
    val title: String,
    @field:NotBlank(message = "내용을 입력해주세요")
    val content: String
)
