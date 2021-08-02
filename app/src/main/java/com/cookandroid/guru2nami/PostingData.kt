package com.cookandroid.guru2nami

//글쓰기 등록하기 위한 데이터 구조

import android.widget.RadioButton
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class PostingData(val perTitle: String? = null, //글 제목
                       val product2: String? = null,  //나눔 물품명
                       val methodTrans : String? = null, //나눔방법
                       val category2: String? = null,  //카테고리
                       val hopeArea: String? = null,  //희망지역
                       val howTrans: String? = null,  //거래방식
                       val content2: String? = null  //상세내용
//                       val nickName: String? = null,  //게시자 닉네임
//                       val likeCount: Int? = null,  //좋아요 수 카운트
//                       val date: String? = null  //날짜

) {}

