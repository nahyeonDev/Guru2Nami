package com.cookandroid.guru2nami.User

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.cookandroid.guru2nami.MainActivity
import com.cookandroid.guru2nami.R
import com.google.firebase.auth.FirebaseAuth


//로그인
class LoginActivity : AppCompatActivity() {

    lateinit var loginBtn: Button //로그인 버튼
    lateinit var signUpBtn: Button //회원가입 버튼
    lateinit var auth: FirebaseAuth
    lateinit var makeId: EditText
    lateinit var makePw: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn = findViewById(R.id.loginBtn)
        signUpBtn = findViewById(R.id.signUpBtn)
        makeId = findViewById(R.id.userId)
        makePw = findViewById(R.id.userPw)

        auth = FirebaseAuth.getInstance()

        /*회원가입 버튼으로 해당화면으로 전환*/
        signUpBtn.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignInActivity::class.java)
            startActivity(intent)
        }
        loginBtn.setOnClickListener {
            val userId = makeId.text.toString().trim()
            val userPw = makePw.text.toString().trim()

            loginUser(userId, userPw)
        }
    }

    private fun loginUser(userId: String, userPw: String) {
        if (userId.isEmpty()) {
            Toast.makeText(this, "아이디를 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (userPw.isEmpty()) {
            Toast.makeText(this, "비밀번호를 작성해주세요.", Toast.LENGTH_LONG).show()
        } else {
            auth.signInWithEmailAndPassword(userId, userPw)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "userLogin:success")
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.w(TAG, "userLogin:failure", task.exception)
                        Toast.makeText(
                            this,
                            "Error Message: " + task.exception?.message.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}