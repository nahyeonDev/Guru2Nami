package com.cookandroid.guru2nami.Markers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.cookandroid.guru2nami.R
import com.naver.maps.map.overlay.InfoWindow

//나눔
class MarkerAdapter2(private val mContext: Context, private val mParent: ViewGroup) :
        InfoWindow.DefaultViewAdapter(mContext) {
    override fun getContentView(infoWindow: InfoWindow): View {
        val view = LayoutInflater.from(context).inflate(com.cookandroid.guru2nami.R.layout.item_point, null)
        val title = view.findViewById<TextView>(com.cookandroid.guru2nami.R.id.markTitle)
        val img = view.findViewById<ImageView>(com.cookandroid.guru2nami.R.id.markContentImg)
        val name = view.findViewById<TextView>(com.cookandroid.guru2nami.R.id.markUserId)
        val content = view.findViewById<TextView>(com.cookandroid.guru2nami.R.id.markContent)

        title.text = "채소 나눔"
        name.text = "tt@naver.com"
        content.text = "채소가 많이 남아 채소 나눔 합니다!"
        img.setImageResource(R.drawable.info2)
        return view
    }
}