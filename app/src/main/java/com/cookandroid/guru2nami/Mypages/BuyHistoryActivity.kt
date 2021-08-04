package com.cookandroid.guru2nami.Mypages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.guru2nami.Adapters.BuyListAdapter
import com.cookandroid.guru2nami.Adapters.ListPersonalAdapter
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Buy
import com.cookandroid.guru2nami.User.Personal
import com.google.firebase.database.*

class BuyHistoryActivity : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList : ArrayList<Buy>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_history)

        userRecyclerView = findViewById(R.id.recycler_buy)
        userRecyclerView.layoutManager = LinearLayoutManager(this);
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<Buy>()
        getUserData()
    }

    private fun getUserData(){

        dbref = FirebaseDatabase.getInstance().getReference("Buy")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(Buy::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = BuyListAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}