package com.cookandroid.guru2nami.User

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.guru2nami.MainActivity
import com.cookandroid.guru2nami.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

//회원가입
class SignInActivity : AppCompatActivity() {

    private lateinit var btnSignOk: Button
    private lateinit var makeId: EditText
    lateinit var makeName : EditText
    lateinit var makePw: EditText
    lateinit var confirmPw: EditText
    lateinit var userAddress: EditText
    lateinit var auth: FirebaseAuth
    private lateinit var refusers: DatabaseReference
    private var firebaseUserID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = Firebase.auth

        btnSignOk = findViewById(R.id.btnSignOk) //회원가입 ok 버튼
        makeId = findViewById(R.id.makeId)
        makeName = findViewById(R.id.makeName)
        makePw = findViewById(R.id.makePw)
        confirmPw = findViewById(R.id.confirmPw)
        userAddress = findViewById(R.id.userAddress)

        btnSignOk.setOnClickListener {
            val userId = makeId.text.toString().trim()
            val userName = makeName.text.toString().trim()
            val userPw = makePw.text.toString().trim()
            val userConfirmPw = confirmPw.text.toString()
            val userAddr = userAddress.text.toString()

            registerUser(userId, userName, userPw, userConfirmPw, userAddr)

        }
    }
    private fun registerUser(
            userId: String,
            userName: String,
            userPw: String,
            userConfirmPw: String,
            userAddr: String
    ) {

        if (userId.isEmpty()) {
            Toast.makeText(this, "아이디를 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (userName.isEmpty()) {
            Toast.makeText(this, "이름을 작성해주세요.", Toast.LENGTH_LONG).show()
        }  else if (userPw.isEmpty()) {
            Toast.makeText(this, "비밀번호를 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (userConfirmPw.isEmpty()) {
            Toast.makeText(this, "비밀번호 확인란을 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (userConfirmPw != userPw) {
            Toast.makeText(this, "비밀번호가 다릅니다.", Toast.LENGTH_LONG).show()
        }else if (userAddr.isEmpty()) {
            Toast.makeText(this, "주소를 작성해주세요.", Toast.LENGTH_LONG).show()
        } else {
            auth.createUserWithEmailAndPassword(userId, userPw)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithIdAndPw:success")
                        val user: FirebaseUser? = auth.currentUser //현재 로그인한 사용자
                        if (user != null) {
                            firebaseUserID = user.uid
                        }
                        refusers = FirebaseDatabase.getInstance().reference.child("Users")
                            .child(firebaseUserID)

                        val userHashMap = HashMap<String, Any>()
                        userHashMap["uid"] = firebaseUserID
                        userHashMap["userId"] = userId
                        userHashMap["userName"] = userName
                        userHashMap["userPw"] = userPw
                        userHashMap["userAddr"] = userAddr

                        refusers.updateChildren(userHashMap)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val intent =
                                        Intent(this@SignInActivity, MainActivity::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)
                                    finish()
                                }
                            }

                    } else {
                        Log.w(TAG, "createUserWithIdAndPw:failure", task.exception)
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

