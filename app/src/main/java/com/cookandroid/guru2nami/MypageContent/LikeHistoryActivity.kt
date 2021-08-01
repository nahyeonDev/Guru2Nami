package com.cookandroid.guru2nami.MypageContent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.cookandroid.guru2nami.Adapters.BuyListAdapter
import com.cookandroid.guru2nami.Adapters.LikeListAdapter
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.BuyHistory
import com.cookandroid.guru2nami.User.LikeHistory

//찜 내역
class LikeHistoryActivity : AppCompatActivity() {

    lateinit var likeListView : ListView

    var likeList = arrayListOf<LikeHistory>(
        LikeHistory("8/1(일)","당근 5개 나눔합니다~"),
        LikeHistory("8/1(일)","당근 5개 나눔합니다~"),

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like_history)

       likeListView = findViewById(R.id.like_list)

        var Adapter = LikeListAdapter(this, likeList)
        likeListView.adapter = Adapter
    }
}