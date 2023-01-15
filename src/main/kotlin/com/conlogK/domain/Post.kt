package com.conlogK.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Lob

@Entity
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var title: String,
    @Lob // DB에서 LongText 형태로 생성되도록 함
    val content: String
) {
    // 서비스의 정책을 엔티티에 넣지 말고 서비스 정책에 맞는 응답 클래스를 만들거라
    /*fun getSubTitle(): String {
        return title.substring(0, 10)
    }*/
    fun getSubStrTitle(): String {
        return title.substring(0, 10)
    }
}
