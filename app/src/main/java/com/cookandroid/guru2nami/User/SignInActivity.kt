package com.cookandroid.guru2nami.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.cookandroid.guru2nami.R

//회원가입
class SignInActivity : AppCompatActivity() {

    lateinit var btnSignOk : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btnSignOk = findViewById(R.id.btnSignOk)

        btnSignOk.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}