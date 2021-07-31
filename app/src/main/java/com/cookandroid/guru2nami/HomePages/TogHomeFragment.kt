package com.cookandroid.guru2nami.HomePages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.cookandroid.guru2nami.Adapters.ListPersonalAdapter
import com.cookandroid.guru2nami.Adapters.ListTogetherAdapter
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Personal
import com.cookandroid.guru2nami.User.Together

//홈. 상단 탭(공구 이벤트)을 눌렀을때 나오는 fragment
//리스트뷰와 카드뷰를 연결함
class TogHomeFragment : Fragment() {

    lateinit var listTogether : ListView
    lateinit var togView : View

    var TogList = arrayListOf<Together>(
            Together(R.drawable.food, R.drawable.food,R.drawable.food,
                    R.drawable.food,R.drawable.ic_launcher_foreground, "user1","1분전",
                    "엽떡 배달비 절약 공구하실 분 구합니다!"),
            Together(R.drawable.food, R.drawable.food,R.drawable.food,
                    R.drawable.food,R.drawable.ic_launcher_foreground, "user2","2분전",
                    "엽떡 배달비 절약 공구하실 분 구합니다!"),
            Together(R.drawable.food, R.drawable.food,R.drawable.food,
                    R.drawable.food,R.drawable.ic_launcher_foreground, "user3","3분전",
                    "엽떡 배달비 절약 공구하실 분 구합니다!"),
            Together(R.drawable.food, R.drawable.food,R.drawable.food,
                    R.drawable.food,R.drawable.ic_launcher_foreground, "user4","4분전",
                    "엽떡 배달비 절약 공구하실 분 구합니다!"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        togView = inflater.inflate(R.layout.fragment_tog_home, container, false)
        listTogether = togView.findViewById(R.id.list_view_together)
        return togView
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        listTogether.apply {

            var Adapter = ListTogetherAdapter(context, TogList)
            listTogether.adapter = Adapter
        }
    }
}