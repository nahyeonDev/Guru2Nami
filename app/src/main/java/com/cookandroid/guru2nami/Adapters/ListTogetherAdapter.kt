package com.cookandroid.guru2nami.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Together

//공구화면 리스트뷰와 카드뷰 연결 어댑터

class ListTogetherAdapter (val context: Context, val TogList: ArrayList<Together>) : BaseAdapter() {
    override fun getCount(): Int{
        return TogList.size
    }

    override fun getItem(position: Int): Any {
        return TogList[position]
    }
    override fun getItemId(position: Int): Long { return 0 }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.item_tog_list, null)
        val img1 = view.findViewById<ImageView>(R.id.image_main1)
        val img2 = view.findViewById<ImageView>(R.id.image_main2)
        val img3 = view.findViewById<ImageView>(R.id.image_main3)
        val img4 = view.findViewById<ImageView>(R.id.image_main4)
        val profile = view.findViewById<ImageView>(R.id.imgIcon4)
        val name = view.findViewById<TextView>(R.id.profiletext_main)
        val time = view.findViewById<TextView>(R.id.upload_time)
        val content = view.findViewById<TextView>(R.id.content_main)

        val together = TogList[position]

        img1.setImageResource(together.img1)
        img2.setImageResource(together.img2)
        img3.setImageResource(together.img3)
        img4.setImageResource(together.img4)
        profile.setImageResource(together.profile)
        name.text = together.name
        time.text = together.time
        content.text = together.content

        return view
    }
}