package com.cookandroid.guru2nami.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.cookandroid.guru2nami.Adapters.ListRankAdapter
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.UserRank

//랭킹 화면
//리스트뷰와 카드뷰를 연결함
class RankFragment : Fragment() {

    lateinit var listRank : ListView
    lateinit var rankView : View

    var RankList = arrayListOf<UserRank>(
            UserRank("1", R.drawable.ic_launcher_background,"제목1","공구 내용입니다",
                    "나눔/물물교환1"),
            UserRank("2", R.drawable.ic_launcher_background,"제목2","공구 내용입니다",
                    "가격2"),
            UserRank("3", R.drawable.ic_launcher_background,"제목3","공구 내용입니다",
                    "가격3"),
            UserRank("4", R.drawable.ic_launcher_background,"제목4","공구 내용입니다",
                    "나눔/물물교환4"),
            UserRank("5", R.drawable.ic_launcher_background,"제목5","공구 내용입니다",
                    "가격5"),
            )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rankView = inflater.inflate(R.layout.fragment_rank, container, false)
        listRank = rankView.findViewById(R.id.list_rank)
        return rankView
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        listRank.apply {

            var Adapter = ListRankAdapter(context, RankList)
            listRank.adapter = Adapter
        }
    }

}