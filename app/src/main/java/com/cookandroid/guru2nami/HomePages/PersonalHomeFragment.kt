package com.cookandroid.guru2nami.HomePages

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.cookandroid.guru2nami.Adapters.ListPersonalAdapter
import com.cookandroid.guru2nami.Content.PersonalWriteActivity
import com.cookandroid.guru2nami.Content.TogetherWriteActivity
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Personal
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase


//홈. 상단 탭(개인 나눔)을 눌렀을때 나오는 fragment
//리스트뷰와 카드뷰를 연결함
class PersonalHomeFragment : Fragment() {

    lateinit var perView : View

    //리스트뷰 연동을 위한 변수
    lateinit var listPersonal : ListView
    lateinit var clickContent : Button

    //플로팅버튼 애니메이션을 위한 변수
    lateinit var perBtn : FloatingActionButton
    lateinit var togBtn :FloatingActionButton
    lateinit var fabMain :FloatingActionButton

    private var firebaseDatabase: FirebaseDatabase? = null

    var PerList = arrayListOf<Personal>(
        Personal(
            R.drawable.carrot, R.drawable.carrot, R.drawable.carrot,
            R.drawable.carrot, R.drawable.ic_launcher_foreground, "user1", "1분전",
            "시장 당근 한 사람당 한개씩 5개만 선착순으로 나눔합니다~"
        ),
        Personal(
            R.drawable.carrot, R.drawable.carrot, R.drawable.carrot,
            R.drawable.carrot, R.drawable.ic_launcher_foreground, "user2", "2분전",
            "시장 당근 한 사람당 한개씩 5개만 선착순으로 나눔합니다~"
        ),
        Personal(
            R.drawable.carrot, R.drawable.carrot, R.drawable.carrot,
            R.drawable.carrot, R.drawable.ic_launcher_foreground, "user3", "3분전",
            "시장 당근 한 사람당 한개씩 5개만 선착순으로 나눔합니다~"
        ),
        Personal(
            R.drawable.carrot, R.drawable.carrot, R.drawable.carrot,
            R.drawable.carrot, R.drawable.ic_launcher_foreground, "user4", "4분전",
            "시장 당근 한 사람당 한개씩 5개만 선착순으로 나눔합니다~"
        ),
    )

    private var isFabOpen = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        perView = inflater.inflate(R.layout.fragment_personal_home, container, false)
        listPersonal = perView.findViewById(R.id.list_view_personal)

        perBtn = perView.findViewById(R.id.per_btn)
        togBtn = perView.findViewById(R.id.tog_btn)
        fabMain = perView.findViewById(R.id.fav_btn)

        firebaseDatabase = FirebaseDatabase.getInstance()

        return perView
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        listPersonal.apply {

            var Adapter = ListPersonalAdapter(context, PerList)
            listPersonal.adapter = Adapter



        }
        //플로팅 버튼 클릭 시 애니메이션 동작 기능
        fabMain.setOnClickListener {
            toggleFab()
        }
        //나눔 버튼 클릭 시 개인나눔 글쓰기 화면으로 전환
        perBtn.setOnClickListener {
            val intent = Intent(activity, PersonalWriteActivity::class.java)
            startActivity(intent)
        }
        //공구 버튼 클릭 시 공구 글쓰기 화면으로 전환
        togBtn.setOnClickListener {
            val intent = Intent(activity, TogetherWriteActivity::class.java)
            startActivity(intent)
        }

    }

    private fun toggleFab(){
        //플로팅 액션 버튼 닫기/열기
        if(isFabOpen){
            ObjectAnimator.ofFloat(perBtn, "translationY", 0f).apply{start()}
            ObjectAnimator.ofFloat(togBtn, "translationY", 0f).apply{start()}
            fabMain.setImageResource(R.drawable.ic_baseline_create_24)
        }else{
            ObjectAnimator.ofFloat(perBtn, "translationY", -200f).apply{start()}
            ObjectAnimator.ofFloat(togBtn, "translationY", -400f).apply{start()}
            fabMain.setImageResource(R.drawable.ic_baseline_remove_24)
        }
        isFabOpen = !isFabOpen
    }
}


