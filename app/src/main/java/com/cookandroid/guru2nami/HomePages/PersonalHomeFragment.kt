package com.cookandroid.guru2nami.HomePages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.cookandroid.guru2nami.Adapters.ListPersonalAdapter
import com.cookandroid.guru2nami.Adapters.ListRankAdapter
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Personal
import com.cookandroid.guru2nami.User.User

//홈. 상단 탭(개인 나눔)을 눌렀을때 나오는 fragment
//리스트뷰와 카드뷰를 연결함
class PersonalHomeFragment : Fragment() {

    lateinit var listPersonal : ListView
    lateinit var perView : View

    var PerList = arrayListOf<Personal>(
            Personal(R.drawable.carrot, R.drawable.carrot,R.drawable.carrot,
                    R.drawable.carrot,R.drawable.ic_launcher_foreground, "user1","1분전",
                    "시장 당근 한 사람당 한개씩 5개만 선착순으로 나눔합니다~"),
            Personal(R.drawable.carrot, R.drawable.carrot,R.drawable.carrot,
                    R.drawable.carrot,R.drawable.ic_launcher_foreground, "user2","2분전",
                    "시장 당근 한 사람당 한개씩 5개만 선착순으로 나눔합니다~"),
            Personal(R.drawable.carrot, R.drawable.carrot,R.drawable.carrot,
                    R.drawable.carrot,R.drawable.ic_launcher_foreground, "user3","3분전",
                    "시장 당근 한 사람당 한개씩 5개만 선착순으로 나눔합니다~"),
            Personal(R.drawable.carrot, R.drawable.carrot,R.drawable.carrot,
                    R.drawable.carrot,R.drawable.ic_launcher_foreground, "user4","4분전",
                    "시장 당근 한 사람당 한개씩 5개만 선착순으로 나눔합니다~"),
            )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        perView = inflater.inflate(R.layout.fragment_personal_home, container, false)
        listPersonal = perView.findViewById(R.id.list_view_personal)
        return perView
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        listPersonal.apply {

            var Adapter = ListPersonalAdapter(context, PerList)
            listPersonal.adapter = Adapter
        }
    }
}