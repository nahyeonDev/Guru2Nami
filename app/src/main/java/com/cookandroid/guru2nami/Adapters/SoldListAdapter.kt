package com.cookandroid.guru2nami.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Buy
import com.cookandroid.guru2nami.User.Sold

class SoldListAdapter(private val soldList : ArrayList<Sold>) : RecyclerView.Adapter<SoldListAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoldListAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_sold_history,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SoldListAdapter.MyViewHolder, position: Int) {
        val currentitem = soldList[position]

        holder.soldTitle.text = currentitem.soldTitle
    }

    override fun getItemCount(): Int {
        return  soldList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val soldTitle : TextView = itemView.findViewById(R.id.soldTitle)
    }
}