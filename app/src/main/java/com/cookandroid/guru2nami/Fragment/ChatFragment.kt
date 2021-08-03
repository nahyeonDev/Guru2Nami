package com.cookandroid.guru2nami.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Chat
import com.google.firebase.database.DatabaseReference

//채팅 화면
//리스트뷰와 카드뷰를 연결함
class ChatFragment : Fragment() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList : ArrayList<Chat>
    private lateinit var perView : View

    lateinit var chatView : View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        chatView =  inflater.inflate(R.layout.fragment_chat, container, false)

        //userRecyclerView = perView.findViewById(R.id.recycler_chat)
        //userRecyclerView.layoutManager = LinearLayoutManager(activity);
        //userRecyclerView.setHasFixedSize(true)

        return chatView
    }

}