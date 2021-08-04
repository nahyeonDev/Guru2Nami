package com.cookandroid.guru2nami.Content

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.cookandroid.guru2nami.PostingData
import com.cookandroid.guru2nami.PostingData2
import com.cookandroid.guru2nami.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

class TogetherWriteActivity : AppCompatActivity() {
    //firebase
    private var PICK_IMAGE_FROM_ALBUM1 = 0//앨범 픽 변수
    private var PICK_IMAGE_FROM_ALBUM2 = 1//앨범 픽 변수
    private var PICK_IMAGE_FROM_ALBUM3 = 2//앨범 픽 변수
    private var PICK_IMAGE_FROM_ALBUM4 = 3//앨범 픽 변수
    private var storage: FirebaseStorage? = null
    var photoUri: Uri? = null
    private lateinit var database: DatabaseReference

    //이미지 등록
    lateinit var togImgPlus1: ImageView
    lateinit var togImgPlus2: ImageView
    lateinit var togImgPlus3: ImageView
    lateinit var togImgPlus4: ImageView

    //글쓰기 항목들
    lateinit var togTitle: EditText
    lateinit var product: EditText
    lateinit var category: EditText
    lateinit var hopeArea2: EditText
    lateinit var howTrans2: EditText
    lateinit var content3: EditText
    lateinit var togetherRegisterButton: ImageButton
    lateinit var uid : String
    lateinit var userName : String

    //기타
    lateinit var backBtn2: ImageButton

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.action_together_write)

//firebase
        database = Firebase.database.reference
//이미지 등록
        togImgPlus1 = findViewById(R.id.togImgPlus1)
        togImgPlus2 = findViewById(R.id.togImgPlus2)
        togImgPlus3 = findViewById(R.id.togImgPlus3)
        togImgPlus4 = findViewById(R.id.togImgPlus4)
//글쓰기 항목들
        togTitle = findViewById(R.id.togTitle)
        product = findViewById(R.id.product)
        category = findViewById(R.id.category)
        hopeArea2 = findViewById(R.id.hopeArea)
        howTrans2 = findViewById(R.id.howTrans)
        content3 = findViewById(R.id.content2)
        togetherRegisterButton = findViewById(R.id.togetherRegisterButton)
        //기타
        backBtn2 = findViewById(R.id.backBtn2)

//저장소 초기화
        storage = FirebaseStorage.getInstance()

        backBtn2.setOnClickListener{
            onBackPressed()
        }

        togImgPlus1.setOnClickListener{
//앨범 열기
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type= "image/*"
            startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM1)
        }
        togImgPlus2.setOnClickListener{
//앨범 열기
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type= "image/*"
            startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM2)
        }
        togImgPlus3.setOnClickListener{
//앨범 열기
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type= "image/*"
            startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM3)
        }
        togImgPlus4.setOnClickListener{
//앨범 열기
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type= "image/*"
            startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM4)
        }
//등록 버튼 이벤트
        togetherRegisterButton.setOnClickListener{
            posting()
            onBackPressed()
        }
    }

    private fun posting() {
        val togTitle = togTitle.text.toString().trim()
        val product = product.text.toString().trim()
        val category = category.text.toString().trim()
        val hopeArea2 = hopeArea2.text.toString().trim()
        val howTrans2 = howTrans2.text.toString().trim()
        val content3 = content3.text.toString().trim()

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser
        if (user != null) {
            userName = user.email.toString()
            uid = user.uid
        }

        writeNewPost(//글 업로드
            togTitle,
            product,
            category,
            hopeArea2,
            howTrans2,
            content3,
                userName,
                uid
        )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_FROM_ALBUM1) {
            if (resultCode == Activity.RESULT_OK) {
//선택된 이미지 path
                photoUri = data?.data
                togImgPlus1.setImageURI(photoUri)

                val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val imageFileName = "IMAGE_" + timestamp + "_.png"
                val storageRef = storage?.reference?.child("images")?.child(imageFileName)
//파일업로드
                storageRef?.putFile(photoUri!!)?.addOnSuccessListener{}
            } else {
                Toast.makeText(this@TogetherWriteActivity, "사진을 선택해주세요", Toast.LENGTH_SHORT).show()
            }
        }
        else if (requestCode == PICK_IMAGE_FROM_ALBUM2) {
            if (resultCode == Activity.RESULT_OK) {

                photoUri = data?.data
                togImgPlus2.setImageURI(photoUri)

                val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val imageFileName = "IMAGE_" + timestamp + "_.png"
                val storageRef = storage?.reference?.child("images")?.child(imageFileName)

                storageRef?.putFile(photoUri!!)?.addOnSuccessListener{}
            } else {
                Toast.makeText(this@TogetherWriteActivity, "사진을 선택해주세요", Toast.LENGTH_SHORT).show()
            }
        }
        else  if (requestCode == PICK_IMAGE_FROM_ALBUM3) {
            if (resultCode == Activity.RESULT_OK) {

                photoUri = data?.data
                togImgPlus3.setImageURI(photoUri)

                val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val imageFileName = "IMAGE_" + timestamp + "_.png"
                val storageRef = storage?.reference?.child("images")?.child(imageFileName)

                storageRef?.putFile(photoUri!!)?.addOnSuccessListener{}
            } else {
                Toast.makeText(this@TogetherWriteActivity, "사진을 선택해주세요", Toast.LENGTH_SHORT).show()
            }
        }
        else if (requestCode == PICK_IMAGE_FROM_ALBUM4) {
            if (resultCode == Activity.RESULT_OK) {

                photoUri = data?.data
                togImgPlus4.setImageURI(photoUri)

                val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val imageFileName = "IMAGE_" + timestamp + "_.png"
                val storageRef = storage?.reference?.child("images")?.child(imageFileName)

                storageRef?.putFile(photoUri!!)?.addOnSuccessListener{}
            } else {
                Toast.makeText(this@TogetherWriteActivity, "사진을 선택해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun writeNewPost(
        togTitle: String,
        product: String,
        category: String, hopeArea2: String,
        howTrans2: String, content3: String,
        userName: String, uid :String
    ) {
        if (togTitle.isEmpty()) {
            Toast.makeText(this, "글 제목을 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (product.isEmpty()) {
            Toast.makeText(this, "제품명을 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (category.isEmpty()) {
            Toast.makeText(this, "카테고리를 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (hopeArea2.isEmpty()) {
            Toast.makeText(this, "거래 희망 지역을 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (howTrans2.isEmpty()) {
            Toast.makeText(this, "거래 방식을 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (content3.isEmpty()) {
            Toast.makeText(this, "상세내용을 작성해주세요.", Toast.LENGTH_LONG).show()
        } else {

            val key = database.child("PostingData2").push().key
            if (key == null) {
                Log.w(ContentValues.TAG, "Couldn't get push key for posts")
                return
            }
            val newPost2 = PostingData2(
                togTitle,
                product,
                category,
                hopeArea2,
                howTrans2,
                content3,
                    userName,
                    uid
            )
            database.child("PostingData2").child(key).setValue(newPost2).addOnSuccessListener{
                Toast.makeText(
                    this@TogetherWriteActivity,
                    "업로드 성공!:)",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}