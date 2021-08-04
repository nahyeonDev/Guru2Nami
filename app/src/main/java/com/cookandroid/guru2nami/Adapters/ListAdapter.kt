package com.cookandroid.guru2nami.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.BuyHistory
import com.cookandroid.guru2nami.User.Chat

//채팅화면 리스트뷰와 카드뷰 연결 어댑터

class ListAdapter(private val chatList : ArrayList<Chat>) : RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_list,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListAdapter.MyViewHolder, position: Int) {
        val currentitem = chatList[position]

        holder.chatTitle.text = currentitem.chatTitle
        holder.userId.text = currentitem.userId

    }

    override fun getItemCount(): Int {
        return  chatList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val chatTitle : TextView = itemView.findViewById(R.id.chatTitle)
        val userId : TextView = itemView.findViewById(R.id.user_chat)
    }

}