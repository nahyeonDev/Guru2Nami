package com.cookandroid.guru2nami.Mypages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.guru2nami.Adapters.LikeListAdapter
import com.cookandroid.guru2nami.Adapters.SoldListAdapter
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Like
import com.cookandroid.guru2nami.User.Sold
import com.google.firebase.database.*

class SoldHistoryActivity : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList : ArrayList<Sold>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sold_history)

        userRecyclerView = findViewById(R.id.recycler_sold)
        userRecyclerView.layoutManager = LinearLayoutManager(this);
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<Sold>()
        getUserData()
    }

    //sold에서 주문내역에 필요한 것들 읽어옴
    private fun getUserData(){

        dbref = FirebaseDatabase.getInstance().getReference("Sold")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(Sold::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = SoldListAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}