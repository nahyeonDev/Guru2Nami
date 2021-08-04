package com.cookandroid.guru2nami.Markers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.cookandroid.guru2nami.R
import com.naver.maps.map.overlay.InfoWindow

//공구
class MarkerAdapter4 (private val mContext: Context, private val mParent: ViewGroup) :
        InfoWindow.DefaultViewAdapter(mContext) {
    override fun getContentView(infoWindow: InfoWindow): View {
        val view = LayoutInflater.from(context).inflate(com.cookandroid.guru2nami.R.layout.item_point, null)
        val title = view.findViewById<TextView>(com.cookandroid.guru2nami.R.id.markTitle)
        val img = view.findViewById<ImageView>(com.cookandroid.guru2nami.R.id.markContentImg)
        val name = view.findViewById<TextView>(com.cookandroid.guru2nami.R.id.markUserId)
        val content = view.findViewById<TextView>(com.cookandroid.guru2nami.R.id.markContent)

        title.text = "전통과자 공구"
        name.text = "123456@aaa.com"
        content.text = "oo사이트 타임딜 전통 과자 공구합니다~"
        img.setImageResource(R.drawable.info4)
        return view
    }
}