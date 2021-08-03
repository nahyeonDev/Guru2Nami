package com.cookandroid.guru2nami

//글쓰기(나눔) 등록하기 위한 데이터 구조

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class PostingData(
        val perTitle: String? = null, //글 제목
        val product2: String? = null,  //나눔 물품명
        val methodTrans: String? = null, //나눔방법
        val category2: String? = null,  //카테고리
        val hopeArea: String? = null,  //희망지역
        val howTrans: String? = null, //거래방식
        val content2: String? = null,  //상세내용
        val uId: String? = null,  //게시자 정보
        val userId: String? = null,  //게시자 아이디(이메일)
       //val date: String? = null  //날짜
)

