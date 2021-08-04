package com.cookandroid.guru2nami.Fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.cookandroid.guru2nami.MypageContent.BuyHistoryActivity
import com.cookandroid.guru2nami.MypageContent.LikeHistoryActivity
import com.cookandroid.guru2nami.MypageContent.SalesHistoryActivity
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*


//마이페이지 화면
class MyPageFragment : Fragment() {
    private var mAuth: FirebaseAuth? = null
    private var PICK_IMAGE_FROM_ALBUM = 0//앨범 픽 변수
    private var storage: FirebaseStorage? = null
    var photoUri: Uri? = null
    private lateinit var database: DatabaseReference
    lateinit var userName: TextView
    lateinit var userImg: ImageView
    lateinit var myPageView: View
    lateinit var listView: ListView

    //각 화면으로 넘어가는 버튼
    lateinit var soldListBtn: Button //판매내역
    lateinit var buyListBtn: Button //주문내역
    lateinit var likeListBtn: Button //찜 내역

    //로그아웃 버튼, 회원탈퇴 버튼
    lateinit var logOut: Button
    lateinit var removeUser: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myPageView = inflater.inflate(R.layout.fragment_my_page, container, false)
        //프로필 이미지
        userImg = myPageView.findViewById(R.id.userImage)
        userImg.setOnClickListener {
            //앨범 열기
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM)
        }
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

    //사진 선택 & 업로드
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_FROM_ALBUM) {
            if (resultCode == Activity.RESULT_OK) {
//선택된 이미지 path
                photoUri = data?.data
                userImg.setImageURI(photoUri)

                val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val imageFileName = "IMAGE_" + timestamp + "_.png"
                val storageRef = storage?.reference?.child("images")?.child(imageFileName)
//파일업로드
                storageRef?.putFile(photoUri!!)?.addOnSuccessListener {
                    Toast.makeText(activity, "프로필 사진 설정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(activity, "프로필 사진을 선택해주세요", Toast.LENGTH_SHORT).show()
            }
        }


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