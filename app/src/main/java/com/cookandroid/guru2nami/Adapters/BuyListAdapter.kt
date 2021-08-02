package com.cookandroid.guru2nami.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.BuyHistory
import com.cookandroid.guru2nami.User.User

//주문내역 화면 리스트뷰, 카드뷰 연결 어댑터
class BuyListAdapter(val context: Context, val BuyList: ArrayList<BuyHistory>) : BaseAdapter(){
    override fun getCount(): Int{
        return BuyList.size
    }

    override fun getItem(position: Int): Any {
        return BuyList[position]
    }
    override fun getItemId(position: Int): Long { return 0 }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.item_buy_history, null)
        val time = view.findViewById<TextView>(R.id.buyTime)
        val result = view.findViewById<TextView>(R.id.buyRes)
        val title = view.findViewById<TextView>(R.id.buyTitle)

        val user = BuyList[position]

        time.text = user.time
        result.text = user.result
        title.text = user.title

        return view
    }
}