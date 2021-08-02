package com.cookandroid.guru2nami.MypageContent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.cookandroid.guru2nami.Adapters.SalesListAdapter
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.BuyHistory
import com.cookandroid.guru2nami.User.SalesHistory

//판매내역
class SalesHistoryActivity : AppCompatActivity() {

    lateinit var salesListView : ListView

    var salesList = arrayListOf<SalesHistory>(
        SalesHistory("8/1(일)","진행중","당근 5개 나눔합니다~"),
        SalesHistory("7/30(금)","완료","당근 5개 나눔합니다~"),

        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales_history)

        salesListView = findViewById(R.id.sales_list)

        var Adapter = SalesListAdapter(this, salesList)
        salesListView.adapter = Adapter
    }
}