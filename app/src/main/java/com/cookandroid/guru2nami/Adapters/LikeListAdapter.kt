package com.cookandroid.guru2nami.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Buy
import com.cookandroid.guru2nami.User.Like

class LikeListAdapter(private val likeList : ArrayList<Like>) : RecyclerView.Adapter<LikeListAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeListAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_like_list,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LikeListAdapter.MyViewHolder, position: Int) {
        val currentitem = likeList[position]

        holder.likeTitle.text = currentitem.likeTitle
    }

    override fun getItemCount(): Int {
        return likeList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val likeTitle : TextView = itemView.findViewById(R.id.likeTitle)
    }
}