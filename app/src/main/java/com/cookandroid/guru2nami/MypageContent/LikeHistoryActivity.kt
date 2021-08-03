package com.cookandroid.guru2nami.MypageContent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.guru2nami.Adapters.BuyListAdapter
import com.cookandroid.guru2nami.Adapters.LikeListAdapter
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.BuyHistory
import com.cookandroid.guru2nami.User.Chat
import com.cookandroid.guru2nami.User.LikeHistory
import com.google.firebase.database.DatabaseReference

//찜 내역
class LikeHistoryActivity : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList : ArrayList<LikeHistory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like_history)

        //userRecyclerView = findViewById(R.id.recycler_like)
        //userRecyclerView.layoutManager = LinearLayoutManager(this);
        //userRecyclerView.setHasFixedSize(true)

    }
}