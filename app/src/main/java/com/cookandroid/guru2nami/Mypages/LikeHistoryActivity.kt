package com.cookandroid.guru2nami.Mypages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.guru2nami.Adapters.BuyListAdapter
import com.cookandroid.guru2nami.Adapters.LikeListAdapter
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Buy
import com.cookandroid.guru2nami.User.Like
import com.google.firebase.database.*

class LikeHistoryActivity : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList : ArrayList<Like>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like_history)

        userRecyclerView = findViewById(R.id.recycler_like)
        userRecyclerView.layoutManager = LinearLayoutManager(this);
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<Like>()
        getUserData()
    }

    private fun getUserData(){

        dbref = FirebaseDatabase.getInstance().getReference("Like")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(Like::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = LikeListAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}