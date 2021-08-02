package com.cookandroid.guru2nami.Content

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.content.ContextCompat
import com.cookandroid.guru2nami.R
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

//개인나눔 글쓰는 페이지
class PersonalWriteActivity : AppCompatActivity() {
    private var PICK_IMAGE_FROM_ALBUM=0 //앨범 픽 변수
    private var storage:FirebaseStorage?=null
    var photoUri: Uri?=null
    lateinit var perImgPlus:ImageView
    lateinit var perTitle:EditText
    lateinit var Product2:EditText
    lateinit var sharingMethod:RadioGroup
    lateinit var category2: EditText
    lateinit var hopeArea:EditText
    lateinit var howTrans:EditText
    lateinit var content2:EditText
    lateinit var personalRegisterButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.action_personal_write)

        perTitle=findViewById(R.id.perTitle)
        Product2=findViewById(R.id.Product2)
        perImgPlus=findViewById(R.id.perImgPlus1)
        sharingMethod=findViewById(R.id.sharingMethod)
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
}