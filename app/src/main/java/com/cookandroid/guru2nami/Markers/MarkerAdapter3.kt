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
class MarkerAdapter3(private val mContext: Context, private val mParent: ViewGroup) :
        InfoWindow.DefaultViewAdapter(mContext) {
    override fun getContentView(infoWindow: InfoWindow): View {
        val view = LayoutInflater.from(context).inflate(com.cookandroid.guru2nami.R.layout.item_point, null)
        val title = view.findViewById<TextView>(com.cookandroid.guru2nami.R.id.markTitle)
        val img = view.findViewById<ImageView>(com.cookandroid.guru2nami.R.id.markContentImg)
        val name = view.findViewById<TextView>(com.cookandroid.guru2nami.R.id.markUserId)
        val content = view.findViewById<TextView>(com.cookandroid.guru2nami.R.id.markContent)

        title.text = "생선 공동구매 :)"
        name.text = "fishfish@daum.net"
        content.text = "공구로 구매해 싼 값에 맛있는 생선 먹고싶으신 분들 연락주세요"
        img.setImageResource(R.drawable.info3)
        return view
    }
}