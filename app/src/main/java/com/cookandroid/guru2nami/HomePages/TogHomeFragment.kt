package com.cookandroid.guru2nami.HomePages

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.cookandroid.guru2nami.Adapters.ListPersonalAdapter
import com.cookandroid.guru2nami.Adapters.ListTogetherAdapter
import com.cookandroid.guru2nami.Content.PersonalWriteActivity
import com.cookandroid.guru2nami.Content.TogetherWriteActivity
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Personal
import com.cookandroid.guru2nami.User.Together
import com.google.android.material.floatingactionbutton.FloatingActionButton

//홈. 상단 탭(공구 이벤트)을 눌렀을때 나오는 fragment
//리스트뷰와 카드뷰를 연결함
class TogHomeFragment : Fragment() {

    lateinit var listTogether : ListView
    lateinit var togView : View

    //플로팅버튼 애니메이션을 위한 변수
    lateinit var perBtn2 : FloatingActionButton
    lateinit var togBtn2 : FloatingActionButton
    lateinit var fabMain2 : FloatingActionButton

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

    private var isFabOpen = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        togView = inflater.inflate(R.layout.fragment_tog_home, container, false)
        listTogether = togView.findViewById(R.id.list_view_together)

        perBtn2 = togView.findViewById(R.id.per_btn)
        togBtn2 = togView.findViewById(R.id.tog_btn)
        fabMain2 = togView.findViewById(R.id.fav_btn)

        return togView
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        listTogether.apply {

            var Adapter = ListTogetherAdapter(context, TogList)
            listTogether.adapter = Adapter
        }

        //플로팅 버튼 클릭 시 애니메이션 동작 기능
        fabMain2.setOnClickListener {
            toggleFab2()
        }
        //나눔 버튼 클릭 시 개인나눔 글쓰기 화면으로 전환
        perBtn2.setOnClickListener {
            val intent = Intent(activity, PersonalWriteActivity ::class.java)
            startActivity(intent)
        }
        //공구 버튼 클릭 시 공구 글쓰기 화면으로 전환
        togBtn2.setOnClickListener {
            val intent = Intent(activity, TogetherWriteActivity ::class.java)
            startActivity(intent)
        }
    }

    private fun toggleFab2(){
        //플로팅 액션 버튼 닫기/열기
        if(isFabOpen){
            ObjectAnimator.ofFloat(perBtn2, "translationY",0f).apply{start()}
            ObjectAnimator.ofFloat(togBtn2, "translationY",0f).apply{start()}
            fabMain2.setImageResource(R.drawable.ic_baseline_create_24)
        }else{
            ObjectAnimator.ofFloat(perBtn2, "translationY",-200f).apply{start()}
            ObjectAnimator.ofFloat(togBtn2, "translationY",-400f).apply{start()}
            fabMain2.setImageResource(R.drawable.ic_baseline_remove_24)
        }
        isFabOpen = !isFabOpen
    }
}