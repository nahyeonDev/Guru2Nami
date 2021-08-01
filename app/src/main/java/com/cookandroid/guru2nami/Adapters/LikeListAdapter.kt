package com.cookandroid.guru2nami.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.LikeHistory

//찜내역 화면 리스트뷰, 카드뷰 연결 어댑터
class LikeListAdapter(val context: Context, val LikeList: ArrayList<LikeHistory>) : BaseAdapter(){
    override fun getCount(): Int{
        return LikeList.size
    }

    override fun getItem(position: Int): Any {
        return LikeList[position]
    }
    override fun getItemId(position: Int): Long { return 0 }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.item_like_history, null)
        val time = view.findViewById<TextView>(R.id.likeTime)
        val title = view.findViewById<TextView>(R.id.likeTitle)

        val user = LikeList[position]

        time.text = user.time
        title.text = user.title

        return view
    }
}