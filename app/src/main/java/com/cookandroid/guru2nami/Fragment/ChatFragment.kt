package com.cookandroid.guru2nami.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.guru2nami.Adapters.ListAdapter
import com.cookandroid.guru2nami.Adapters.ListPersonalAdapter
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Chat
import com.cookandroid.guru2nami.User.Personal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

//채팅 화면
//리스트뷰와 카드뷰를 연결함
class ChatFragment : Fragment() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList : ArrayList<Chat>

    lateinit var chatView : View

    private var mAuth: FirebaseAuth? = null
    lateinit var uid : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        chatView =  inflater.inflate(R.layout.fragment_chat, container, false)

        userRecyclerView = chatView.findViewById(R.id.recycler_chat)
        userRecyclerView.layoutManager = LinearLayoutManager(activity);
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<Chat>()
        getUserData()

        return chatView
    }

    private fun getUserData(){

        dbref = FirebaseDatabase.getInstance().getReference("Chat")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(Chat::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = ListAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}