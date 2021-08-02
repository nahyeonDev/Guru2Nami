package com.cookandroid.guru2nami.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.BuyHistory
import com.cookandroid.guru2nami.User.SalesHistory

//판매내역 화면 리스트뷰, 카드뷰 연결 어댑터
class SalesListAdapter(val context: Context, val SalesList: ArrayList<SalesHistory>) : BaseAdapter(){
    override fun getCount(): Int{
        return SalesList.size
    }

    override fun getItem(position: Int): Any {
        return SalesList[position]
    }
    override fun getItemId(position: Int): Long { return 0 }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.item_sales_history, null)
        val time = view.findViewById<TextView>(R.id.salesTime)
        val result = view.findViewById<TextView>(R.id.salesRes)
        val title = view.findViewById<TextView>(R.id.salesTitle)

        val user = SalesList[position]

        time.text = user.time
        result.text = user.result
        title.text = user.title

        return view
    }
}