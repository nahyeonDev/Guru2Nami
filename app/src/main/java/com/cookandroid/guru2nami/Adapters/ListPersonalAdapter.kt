package com.cookandroid.guru2nami.Adapters

import android.Manifest
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Personal

//개인나눔화면 리스트뷰와 카드뷰 연결 어댑터

class ListPersonalAdapter(val context: Context, val PerList: ArrayList<Personal>) : BaseAdapter() {
    override fun getCount(): Int{
        return PerList.size
    }

    override fun getItem(position: Int): Any {
        return PerList[position]
    }
    override fun getItemId(position: Int): Long { return 0 }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.item_per_list, null)
        val img1 = view.findViewById<ImageView>(R.id.image_main1)
        val img2 = view.findViewById<ImageView>(R.id.image_main2)
        val img3 = view.findViewById<ImageView>(R.id.image_main3)
        val img4 = view.findViewById<ImageView>(R.id.image_main4)
        val profile = view.findViewById<ImageView>(R.id.imgIcon3)
        val name = view.findViewById<TextView>(R.id.profiletext_main)
        val time = view.findViewById<TextView>(R.id.upload_time)
        val content = view.findViewById<TextView>(R.id.content_main)

        val personal = PerList[position]

        img1.setImageResource(personal.img1)
        img2.setImageResource(personal.img2)
        img3.setImageResource(personal.img3)
        img4.setImageResource(personal.img4)
        profile.setImageResource(personal.profile)
        name.text = personal.name
        time.text = personal.time
        content.text = personal.content

        return view
    }
}