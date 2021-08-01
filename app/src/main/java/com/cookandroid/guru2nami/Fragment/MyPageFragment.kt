package com.cookandroid.guru2nami.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.cookandroid.guru2nami.MypageContent.BuyHistoryActivity
import com.cookandroid.guru2nami.MypageContent.LikeHistoryActivity
import com.cookandroid.guru2nami.MypageContent.SalesHistoryActivity
import com.cookandroid.guru2nami.R
import kotlin.concurrent.fixedRateTimer

//마이페이지 화면
class MyPageFragment : Fragment() {

    lateinit var listView: ListView
    val myPage_List = ArrayList<String>() //마이페이지 항목들을 담을 String 리스트 생성

    //주문내역, 판매내역, 찜내역 버튼
    lateinit var buyBtn : Button
    lateinit var salesBtn : Button
    lateinit var likeBtn : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //View로 전환
        val view:View = inflater.inflate(R.layout.fragment_my_page, container, false)

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

        //주문내역 버튼 누르면 화면 전환
        buyBtn = view.findViewById(R.id.buyListBtn)
        buyBtn.setOnClickListener {
            var intent = Intent(activity, BuyHistoryActivity::class.java)
            startActivity(intent)
        }

        //판매내역 버튼 누르면 화면 전환
        salesBtn = view.findViewById(R.id.soldListBtn)
        salesBtn.setOnClickListener {
            var intent = Intent(activity, SalesHistoryActivity::class.java)
            startActivity(intent)
        }

        likeBtn = view.findViewById(R.id.likeListBtn)
        likeBtn.setOnClickListener {
            var intent = Intent(activity, LikeHistoryActivity::class.java)
            startActivity(intent)
        }
        return view
    }

}