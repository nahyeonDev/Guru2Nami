package com.cookandroid.guru2nami.MypageContent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.cookandroid.guru2nami.Adapters.BuyListAdapter
import com.cookandroid.guru2nami.Adapters.ListAdapter
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.BuyHistory
import com.cookandroid.guru2nami.User.User

//주문내역
class BuyHistoryActivity : AppCompatActivity() {

    lateinit var buyListView : ListView

    var buyList = arrayListOf<BuyHistory>(
        BuyHistory("8/1(일)","대기","당근 5개 나눔합니다~"),
        BuyHistory("7/30(금)","주문완료","당근 5개 나눔합니다~"),

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_history)

        buyListView = findViewById(R.id.buy_list)

        var Adapter = BuyListAdapter(this, buyList)
        buyListView.adapter = Adapter

    }
}