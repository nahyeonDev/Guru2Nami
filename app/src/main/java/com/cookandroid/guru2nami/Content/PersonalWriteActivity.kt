package com.cookandroid.guru2nami.Content

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cookandroid.guru2nami.Adapters.ListPersonalAdapter
import com.cookandroid.guru2nami.ChatPosting
import com.cookandroid.guru2nami.HomePages.PersonalHomeFragment
import com.cookandroid.guru2nami.PostingData
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.SoldPosting
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
//import kotlinx.android.synthetic.main.action_personal_write.*
import java.text.SimpleDateFormat
import java.util.*

//개인나눔 글쓰는 페이지
class PersonalWriteActivity : AppCompatActivity() {
    //firebase
    private var PICK_IMAGE_FROM_ALBUM1 = 0//앨범 픽 변수
    lateinit var storage: FirebaseStorage
    val storageRef = Firebase.storage.reference
    var photoUri: Uri? = null
    private lateinit var database: DatabaseReference
    lateinit var imageFileName : String
    lateinit var imageFileName1 : String
    private var mAuth: FirebaseAuth? = null
    lateinit var homeImageView1: ImageView

    //이미지 등록
    lateinit var perImgPlus1: ImageButton
   // lateinit var perImgPlusBtn : Button

    //글쓰기 항목들
    lateinit var perTitle: EditText
    lateinit var product2: EditText
    lateinit var methodTrans: EditText
    lateinit var category2: EditText
    lateinit var hopeArea: EditText
    lateinit var howTrans: EditText
    lateinit var content2: EditText
    lateinit var personalRegisterButton: ImageButton
    lateinit var uid : String
    lateinit var userName : String
    lateinit var image1 : String //이미지 이름


    //기타
    lateinit var backBtn:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.action_personal_write)

//firebase
        database = Firebase.database.reference
//이미지 등록
        perImgPlus1 = findViewById(R.id.perImgPlus1)
//글쓰기 항목들
        perTitle = findViewById(R.id.perTitle)
        product2 = findViewById(R.id.product2)
        methodTrans = findViewById(R.id.methodTrans)
        category2 = findViewById(R.id.category2)
        hopeArea = findViewById(R.id.hopeArea)
        howTrans = findViewById(R.id.howTrans)
        content2 = findViewById(R.id.content2)
        personalRegisterButton = findViewById(R.id.personalRegisterButton)
        //기타
        backBtn = findViewById(R.id.backBtn)

        //저장소 가져오기
        storage = FirebaseStorage.getInstance()

        backBtn.setOnClickListener{
            onBackPressed()
        }

        //사진추가 버튼 앨범 열기
        perImgPlus1.setOnClickListener{
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type= "image/*"
            startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM1)
        }
        //등록 버튼 이벤트
        personalRegisterButton.setOnClickListener{
            posting()
            soldPosting()
            onBackPressed()
        }
    }

    private fun posting() {
        val perTitle = perTitle.text.toString().trim()
        val product2 = product2.text.toString().trim()
        val methodTrans = methodTrans.text.toString().trim()
        val category2 = category2.text.toString().trim()
        val hopeArea = hopeArea.text.toString().trim()
        val howTrans = howTrans.text.toString().trim()
        val content2 = content2.text.toString().trim()

        //누가 올렸는지 식별하기 위해 글 쓴 회원정보 갖고오기
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser
        if (user != null) {
            userName = user.email.toString()
            uid = user.uid }
        val userName = userName
        val uid = uid
        val image1= image1

        writeNewPost(//글 업로드
                perTitle,
                product2,
                methodTrans,
                category2,
                hopeArea,
                howTrans,
                content2,
                userName,
                uid,
                image1
        )

    }

    private fun soldPosting() {
        val soldTitle= perTitle.text.toString()

        makeSoldPost(//글 업로드
                soldTitle
        )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_FROM_ALBUM1) {
            if (resultCode == RESULT_OK) {
                //선택된 이미지 path
                photoUri = data?.data
                perImgPlus1.setImageURI(photoUri)
                perImgPlus1.setOnClickListener{
                    setPerImgPlus(photoUri)
                }

                //날짜로 파일명 지정 후 스토리지 참조해 파일업로드
                val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val imageFileName = "IMAGE_" + timestamp + "_.png"
                image1 = imageFileName //설정한 이미지 이름을 넣어줌(url 만들 때 필요함)
                val storageRef = storage?.reference?.child("images")?.child(imageFileName)
                //파일업로드
                storageRef?.putFile(photoUri!!)?.addOnSuccessListener{}

            } else {
                Toast.makeText(this@PersonalWriteActivity, "사진을 선택해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setPerImgPlus(uri: Uri?){
        perImgPlus1.setImageURI(uri)
    }

    private fun writeNewPost(
            perTitle: String,
            product2: String, methodTrans: String,
            category2: String, hopeArea: String,
            howTrans: String, content2: String,
            userName: String, uid: String, image1 : String
    ) {
        if (perTitle.isEmpty()) {
            Toast.makeText(this, "글 제목을 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (product2.isEmpty()) {
            Toast.makeText(this, "제품명을 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (methodTrans.isEmpty()) {
            Toast.makeText(this, "나눔 방법을 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (category2.isEmpty()) {
            Toast.makeText(this, "카테고리를 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (hopeArea.isEmpty()) {
            Toast.makeText(this, "거래 희망 지역을 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (howTrans.isEmpty()) {
            Toast.makeText(this, "거래 방식을 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (content2.isEmpty()) {
            Toast.makeText(this, "상세내용을 작성해주세요.", Toast.LENGTH_LONG).show()
        } else {

            val key = database.child("PostingData").push().key
            if (key == null) {
                Log.w(TAG, "Couldn't get push key for posts")
                return
            }
            val newPost = PostingData(
                    perTitle,
                    product2,
                    methodTrans,
                    category2,
                    hopeArea,
                    howTrans,
                    content2,
                    userName,
                    uid,
                    image1
            )
            database.child("PostingData").child(key).setValue(newPost).addOnSuccessListener{
                Toast.makeText(this@PersonalWriteActivity, "업로드 성공!:)", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun makeSoldPost(
            soldTitle: String
    ) {
        val key = database.child("Sold").push().key
        if (key == null) {
            Log.w(ContentValues.TAG, "Couldn't get push key for posts")
            return
        }
        val newPost = SoldPosting(
                soldTitle
        )
        database.child("Sold").child(key).setValue(newPost).addOnSuccessListener{

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}