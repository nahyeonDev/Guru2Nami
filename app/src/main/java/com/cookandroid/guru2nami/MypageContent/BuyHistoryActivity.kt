package com.cookandroid.guru2nami.MypageContent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.guru2nami.Adapters.BuyListAdapter
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.BuyHistory
import com.cookandroid.guru2nami.User.Chat
import com.google.firebase.database.DatabaseReference

//주문내역
class BuyHistoryActivity : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList : ArrayList<BuyHistory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_history)

        //userRecyclerView = findViewById(R.id.recycler_buy)
        //userRecyclerView.layoutManager = LinearLayoutManager(this);
        //userRecyclerView.setHasFixedSize(true)

    }
}