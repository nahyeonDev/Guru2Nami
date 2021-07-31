package com.cookandroid.guru2nami.User

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.cookandroid.guru2nami.MainActivity
import com.cookandroid.guru2nami.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult

import com.google.android.gms.tasks.OnCompleteListener


//회원가입
class SignInActivity : AppCompatActivity() {

    private lateinit var btnSignOk: Button
    private lateinit var makeId: EditText
    lateinit var makePw: EditText
    lateinit var confirmPw: EditText
    lateinit var userAddress: EditText
    lateinit var IdChkBox: CheckBox
    lateinit var rBtnMale: RadioButton
    lateinit var rBtnFemale: RadioButton
    private lateinit var auth: FirebaseAuth
    lateinit var rGroup: RadioGroup
    private lateinit var refusers: DatabaseReference
    private var firebaseUserID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        btnSignOk = findViewById(R.id.btnSignOk) //회원가입 ok 버튼
        makeId = findViewById(R.id.makeId)
        makePw = findViewById(R.id.makePw)
        confirmPw = findViewById(R.id.confirmPw)
        userAddress = findViewById(R.id.userAddress)
        IdChkBox = findViewById(R.id.IdChkBox)
        rGroup = findViewById(R.id.rGroup)
        rBtnMale = findViewById(R.id.rBtnMale)
        rBtnFemale = findViewById(R.id.rBtnFemale)

        btnSignOk.setOnClickListener {
            val userId = makeId.text.toString().trim()
            val userIdChkBox = IdChkBox.isChecked
            val userPw = makePw.text.toString().trim()
            val userConfirmPw = confirmPw.text.toString()
            val userGroup = rGroup.checkedRadioButtonId
            val userAddr = userAddress.text.toString()

            registerUser(userId, userIdChkBox, userPw, userConfirmPw, userGroup, userAddr)

        }

    }

    private fun registerUser(
        userId: String,
        userIdChkBox: Boolean,
        userPw: String,
        userConfirmPw: String,
        userGroup: Int,
        userAddr: String
    ) {

        if (userId.isEmpty()) {
            Toast.makeText(this, "아이디를 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (!userIdChkBox) {
            Toast.makeText(this, "아이디 중복확인을 해주세요.", Toast.LENGTH_LONG).show()
        } else if (userPw.isEmpty()) {
            Toast.makeText(this, "비밀번호를 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (userConfirmPw.isEmpty()) {
            Toast.makeText(this, "비밀번호 확인란을 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (userGroup == -1) {
            Toast.makeText(this, "성별을 선택해주세요.", Toast.LENGTH_LONG).show()
        } else if (userAddr.isEmpty()) {
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
                        userHashMap["userPw"] = userPw
                        userHashMap["userGroup"] = userGroup
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

