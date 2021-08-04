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
import com.cookandroid.guru2nami.User.Together
import com.cookandroid.guru2nami.User.UserRank
import org.w3c.dom.Text

//랭킹화면 리스트뷰와 카드뷰 연결 어댑터

class ListRankAdapter(private val rankList : ArrayList<UserRank>) : RecyclerView.Adapter<ListRankAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRankAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rank_list,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListRankAdapter.MyViewHolder, position: Int) {
        val currentitem = rankList[position]

        holder.ranking.text = currentitem.ranking
        holder.title.text = currentitem.title
        holder.userId.text = currentitem.userId
        holder.content.text = currentitem.content

    }

    override fun getItemCount(): Int {
        return rankList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val ranking : TextView = itemView.findViewById(R.id.ranking)
        val title : TextView = itemView.findViewById(R.id.main_title)
        val userId : TextView = itemView.findViewById(R.id.rank_user)
        val content: TextView = itemView.findViewById(R.id.rank_content)
    }

}