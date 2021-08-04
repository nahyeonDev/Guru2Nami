package com.cookandroid.guru2nami.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Buy
import com.cookandroid.guru2nami.User.Chat

class BuyListAdapter(private val buyList : ArrayList<Buy>) : RecyclerView.Adapter<BuyListAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyListAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_buy_list,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BuyListAdapter.MyViewHolder, position: Int) {
        val currentitem = buyList[position]

        holder.buyTitle.text = currentitem.buyTitle
    }

    override fun getItemCount(): Int {
        return  buyList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val buyTitle : TextView = itemView.findViewById(R.id.buyTitle)
    }
}