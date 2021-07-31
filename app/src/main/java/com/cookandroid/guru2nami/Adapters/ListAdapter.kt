package com.cookandroid.guru2nami.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.User

//채팅화면 리스트뷰와 카드뷰 연결 어댑터

class ListAdapter(val context: Context, val UserList: ArrayList<User>) : BaseAdapter(){
    override fun getCount(): Int{
        return UserList.size
    }

    override fun getItem(position: Int): Any {
        return UserList[position]
    }
    override fun getItemId(position: Int): Long { return 0 }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{
        val view : View = LayoutInflater.from(context).inflate(R.layout.item_chat_list, null)
        val profile = view.findViewById<ImageView>(R.id.imageIcon)
        val name = view.findViewById<TextView>(R.id.user_id)
        val time = view.findViewById<TextView>(R.id.userTime)
        val content = view.findViewById<TextView>(R.id.contentChat)

        val user = UserList[position]

        profile.setImageResource(user.profile)
        name.text = user.name
        time.text = user.time
        content.text = user.content

        return view
    }

}