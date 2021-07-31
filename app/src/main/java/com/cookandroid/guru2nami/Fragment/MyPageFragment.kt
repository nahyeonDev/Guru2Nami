package com.cookandroid.guru2nami.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import com.cookandroid.guru2nami.R
import kotlin.concurrent.fixedRateTimer

//마이페이지 화면
class MyPageFragment : Fragment() {

    lateinit var listView: ListView
    val myPage_List = ArrayList<String>()  //마이페이지 항목들을 담을 String 리스트 생성

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_page, container, false)

        //View로 전환
        val view:View = inflater.inflate(R.layout.fragment_my_page, container, false);

        val listView:ListView = view.findViewById<ListView>(R.id.myPageListView)

        myPage_List.add("동네 설정하기")
        myPage_List.add("내 글 관리")
        myPage_List.add("고객 센터")
        myPage_List.add("환경 설정")
        myPage_List.add("내 글 관리")

//        //어댑터 생성(data와 view를 연결해주는 관리자)
//        val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,myPage_List)
//        listView.adapter = listAdapter  //어댑터 붙이기
//
//        listView.choiceMode = ListView.CHOICE_MODE_SINGLE  //단일 선택 모드

//        listView.setOnItemClickListener{parent, view, position, id ->
//            Toast.makeText(this, myPage_List.get(position) + "를 클릭하셨습니다.",
//                    Toast.LENGTH_SHORT).show() //선택한 데이터 출력
//        }

    }

}