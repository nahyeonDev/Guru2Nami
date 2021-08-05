package com.cookandroid.guru2nami.User

import com.google.firebase.storage.StorageReference

//개인나눔 화면 게시물정보
data class Personal( var perTitle: String? = null, var hopeArea: String? = null, var content2: String? = null,
                     var category2: String? = null, var userName: String? = null, var uid: String? = null,
                     var image1 : String? =null, var imgDetail : String? = null

                      ){
}
