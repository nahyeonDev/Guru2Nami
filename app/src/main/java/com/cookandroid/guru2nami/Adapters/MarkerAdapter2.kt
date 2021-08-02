package com.cookandroid.guru2nami.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.naver.maps.map.overlay.InfoWindow

//공구 지도 마커의 상세 정보 연결 어댑터
class MarkerAdapter2(private val mContext: Context, private val mParent: ViewGroup) :
    InfoWindow.DefaultViewAdapter(mContext) {
    override fun getContentView(infoWindow: InfoWindow): View {
        val view =
            LayoutInflater.from(context).inflate(com.cookandroid.guru2nami.R.layout.item_point_tog, null)
        val title = view.findViewById<TextView>(com.cookandroid.guru2nami.R.id.mark2Title)
        val img = view.findViewById<ImageView>(com.cookandroid.guru2nami.R.id.mark2ContentImg)
        val name = view.findViewById<TextView>(com.cookandroid.guru2nami.R.id.mark2UserId)
        val content = view.findViewById<TextView>(com.cookandroid.guru2nami.R.id.mark2Content)

        title.text = "배달 공구"
        name.text = "nahyeonDev"
        content.text = "엽떡 배달비 절약을 위해\n 공구하실 분.."
        return view
    }
}