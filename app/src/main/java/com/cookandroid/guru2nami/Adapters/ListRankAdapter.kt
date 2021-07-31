package com.cookandroid.guru2nami.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.UserRank

//랭킹화면 리스트뷰와 카드뷰 연결 어댑터

class ListRankAdapter(val context: Context, val RankList: ArrayList<UserRank>) : BaseAdapter() {
    override fun getCount(): Int{
        return RankList.size
    }

    override fun getItem(position: Int): Any {
        return RankList[position]
    }

    override fun getItemId(position: Int): Long { return 0 }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.item_rank_list, null)
        val ranking = view.findViewById<TextView>(R.id.ranking)
        val rankImg = view.findViewById<ImageView>(R.id.imageIcon2)
        val title = view.findViewById<TextView>(R.id.main_tv)
        val content = view.findViewById<TextView>(R.id.content_tv)
        val price = view.findViewById<TextView>(R.id.price_tv)

        val rank = RankList[position]

        ranking.text = rank.ranking
        rankImg.setImageResource(rank.rankImg)
        title.text = rank.title
        content.text = rank.content
        price.text = rank.price

        return view
    }
}