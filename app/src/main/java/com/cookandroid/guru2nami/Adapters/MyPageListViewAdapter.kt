package com.cookandroid.guru2nami.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.cookandroid.guru2nami.R


//마이페이지 리스트뷰 연결 어댑터

class MyPageListViewAdapter (val context: Context, val myPageList: ArrayList<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return myPageList.size
    }

    override fun getItem(position: Int): Any {
        return myPageList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

//        lateinit var listView : ListView
//        val user = myPageList[position]
//        val view : View = LayoutInflater.from(context).inflate(R.layout.fragment_my_page, null)
//        listView = view.findViewById(R.id.myPageListView)
//
//        return listView

        if (convertView == null) {
            val convertView = LayoutInflater.from(context).inflate(R.layout.item_mypage_list, parent, false)
        }
        val myPageMenuList:TextView = convertView!!.findViewById(R.id.mypageMenuList)
        myPageMenuList.setText(myPageList.get(position))

        return convertView
    }
}