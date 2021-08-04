package com.cookandroid.guru2nami

//글쓰기(공구) 등록하기 위한 데이터 구조

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class PostingData2(
    val togTitle: String? = null, //글 제목
    val product: String? = null,  //나눔 물품명
    val methodTrans2: String? = null, //나눔방법
    val category: String? = null,  //카테고리
    val hopeArea2: String? = null,  //희망지역
    val howTrans2: String? = null, //거래방식
    val content3: String? = null, //상세내용
    val uid: String? = null,  //게시자 정보
    val userName: String? = null,  //게시자 아이디(이메일)
    //val date: String? = null  //날짜
)