package com.cookandroid.guru2nami.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cookandroid.guru2nami.MypageContent.BuyHistoryActivity
import com.cookandroid.guru2nami.MypageContent.LikeHistoryActivity
import com.cookandroid.guru2nami.MypageContent.SalesHistoryActivity
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.LoginActivity
import com.google.firebase.auth.FirebaseAuth


//마이페이지 화면
class MyPageFragment : Fragment() {
    private var mAuth: FirebaseAuth? = null
    lateinit var userName : TextView

    lateinit var myPageView: View
    lateinit var listView : ListView

    //각 화면으로 넘어가는 버튼
    lateinit var soldListBtn :Button //판매내역
    lateinit var buyListBtn :Button //주문내역
    lateinit var likeListBtn :Button //찜 내역

    //로그아웃 버튼, 회원탈퇴 버튼
    lateinit var logOut : Button
    lateinit var removeUser : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        myPageView = inflater.inflate(R.layout.fragment_my_page, container, false)

        //판매내역 버튼 + 화면 이동
        soldListBtn = myPageView.findViewById(R.id.soldListBtn)
        soldListBtn.setOnClickListener {
            val intent = Intent(activity, SalesHistoryActivity::class.java)
            startActivity(intent)
        }

        //주문내역 버튼 + 화면 이동
        buyListBtn = myPageView.findViewById(R.id.buyListBtn)
        buyListBtn.setOnClickListener {
            val intent = Intent(activity, BuyHistoryActivity::class.java)
            startActivity(intent)
        }

        //찜 내역 버튼 + 화면 이동
        likeListBtn = myPageView.findViewById(R.id.likeListBtn)
        likeListBtn.setOnClickListener {
            val intent = Intent(activity, LikeHistoryActivity::class.java)
            startActivity(intent)
        }

        //로그아웃 버튼 누르면 로그아웃 되면서 로그인 화면으로 이동
        //해당 아이디로 재접속 가능
        logOut = myPageView.findViewById(R.id.logOutBtn)
        logOut.setOnClickListener {
            signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

        //회원탈퇴 버튼 누르면 로그아웃 되면서 로그인 화면으로 이동
        //유저 정보 삭제로 인해 해당 아이디로 재접속 불가능
        removeUser = myPageView.findViewById(R.id.removeLogin)
        removeUser.setOnClickListener {
            removeAccess()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

        //현재 유저 이메일을 마이페이지 이름에 받음
        userName = myPageView.findViewById(R.id.userName)
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser
        if (user != null) {
            userName.text = user.email
        }
        return myPageView
    }
    //로그아웃
    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }
    //회원탈퇴
    private fun removeAccess() {
        mAuth!!.currentUser!!.delete()
    }


}