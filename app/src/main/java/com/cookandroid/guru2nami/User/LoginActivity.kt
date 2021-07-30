package com.cookandroid.guru2nami.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.cookandroid.guru2nami.MainActivity
import com.cookandroid.guru2nami.R

//로그인
class LoginActivity : AppCompatActivity() {

    lateinit var loginBtn : Button //로그인 버튼
    lateinit var signUpBtn : Button //회원가입 버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn = findViewById(R.id.loginBtn)
        signUpBtn = findViewById(R.id.signUpBtn)

        /*로그인 버튼으로 메인으로 화면 전환*/
        loginBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        /*회원가입 버튼으로 해당화면으로 전환*/
        signUpBtn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }
}