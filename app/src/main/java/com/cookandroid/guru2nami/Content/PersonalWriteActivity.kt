package com.cookandroid.guru2nami.Content

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.cookandroid.guru2nami.PostingData
import com.cookandroid.guru2nami.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

//개인나눔 글쓰는 페이지
class PersonalWriteActivity : AppCompatActivity() {
    //firebase
    private var PICK_IMAGE_FROM_ALBUM=0 //앨범 픽 변수
    private var storage:FirebaseStorage?=null
    var photoUri: Uri?=null
    private lateinit var database: DatabaseReference
    //이미지 등록
    lateinit var perImgPlus:ImageView
    //글쓰기 항목들
    lateinit var perTitle:EditText
    lateinit var product2:EditText
    lateinit var methodTrans:EditText
    lateinit var category2: EditText
    lateinit var hopeArea:EditText
    lateinit var howTrans:EditText
    lateinit var content2:EditText
    lateinit var personalRegisterButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.action_personal_write)

        //firebase
        database = Firebase.database.reference
        //이미지 등록
        perImgPlus=findViewById(R.id.perImgPlus)
        //글쓰기 항목들
        perTitle=findViewById(R.id.perTitle)
        product2=findViewById(R.id.product2)
        methodTrans=findViewById(R.id.methodTrans)
        category2=findViewById(R.id.category2)
        hopeArea=findViewById(R.id.hopeArea)
        howTrans=findViewById(R.id.howTrans)
        content2=findViewById(R.id.content2)
        personalRegisterButton=findViewById(R.id.personalRegisterButton)


        //저장소 초기화
        storage= FirebaseStorage.getInstance()

        perImgPlus.setOnClickListener{
            //앨범 열기
            var photoPickerIntent=Intent(Intent.ACTION_PICK)
            photoPickerIntent.type="image/*"
            startActivityForResult(photoPickerIntent,PICK_IMAGE_FROM_ALBUM)

            //등록 버튼 이벤트
            personalRegisterButton.setOnClickListener{
                contentUpload()
            }
        }

        personalRegisterButton.setOnClickListener{
            val perTitle = perTitle.text.toString()
            val product2 = product2.text.toString()
            val methodTrans = methodTrans.text.toString()
            val category2 = category2.text.toString()
            val hopeArea = hopeArea.text.toString()
            val howTrans = howTrans.text.toString()
            val content2 = content2.text.toString()

            writeNewPost("1", perTitle, product2, methodTrans, category2, hopeArea, howTrans, content2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==PICK_IMAGE_FROM_ALBUM){
            if(resultCode== Activity.RESULT_OK){
                //선택된 이미지 path
                photoUri=data?.data
                perImgPlus.setImageURI(photoUri)
            }else{
                //사진 선택하지 않고 앨범 나가면 PersonalWriteActivity 나감
                finish()
            }
        }
    }
    @SuppressLint("SimpleDateFormat")
    private fun contentUpload() {
       //파일네임 만들기
        val timestamp=SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName="IMAGE_"+timestamp+"_.png"

        val storageRef=storage?.reference?.child("images")?.child(imageFileName)

        //파일업로드
        storageRef?.putFile(photoUri!!)?.addOnSuccessListener {
            Toast.makeText(this@PersonalWriteActivity,"upload_success",Toast.LENGTH_LONG).show()

        }
    }


    private fun writeNewPost(postID: String, perTitle: String,
                             product2: String, methodTrans: String,
                             category2: String, hopeArea: String,
                             howTrans: String, content2: String
    ) {
        val newPost = PostingData(perTitle, product2, methodTrans, category2, hopeArea, howTrans, content2)
        database.child("PostingData").child(postID).setValue(newPost)
    }
}