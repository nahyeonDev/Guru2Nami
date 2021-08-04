package com.cookandroid.guru2nami

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ChatPosting(
        val chatTitle: String? = null,
        //val uid: String? = null,  //게시자 정보
        val userId: String? = null //게시자 아이디(이메일)
)
