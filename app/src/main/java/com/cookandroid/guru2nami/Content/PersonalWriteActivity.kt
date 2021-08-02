package com.cookandroid.guru2nami.Content

import android.R.attr
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.guru2nami.HomePages.PersonalHomeFragment
import com.cookandroid.guru2nami.PostingData
import com.cookandroid.guru2nami.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*
import android.widget.Toast

import android.content.ClipData

import android.R.attr.data
import java.lang.Exception


//개인나눔 글쓰는 페이지
class PersonalWriteActivity : AppCompatActivity() {
    //firebase
    private var PICK_IMAGE_FROM_ALBUM = 100

    private var storage: FirebaseStorage? = null
    var photoUri: Uri? = null
    private lateinit var database: DatabaseReference

    //이미지 등록
    lateinit var perImgPlus1: ImageView
    lateinit var perImgPlus2: ImageView
    lateinit var perImgPlus3: ImageView
    lateinit var perImgPlus4: ImageView

    //글쓰기 항목들
    lateinit var perTitle: EditText
    lateinit var product2: EditText
    lateinit var methodTrans: EditText
    lateinit var category2: EditText
    lateinit var hopeArea: EditText
    lateinit var howTrans: EditText
    lateinit var content2: EditText
    lateinit var personalRegisterButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.action_personal_write)

        //firebase
        database = Firebase.database.reference
        //이미지 등록
        perImgPlus1 = findViewById(R.id.perImgPlus1)
        perImgPlus2 = findViewById(R.id.perImgPlus2)
        perImgPlus3 = findViewById(R.id.perImgPlus3)
        perImgPlus4 = findViewById(R.id.perImgPlus4)
        //글쓰기 항목들
        perTitle = findViewById(R.id.perTitle)
        product2 = findViewById(R.id.Product2)
        methodTrans = findViewById(R.id.methodTrans)
        category2 = findViewById(R.id.category2)
        hopeArea = findViewById(R.id.hopeArea)
        howTrans = findViewById(R.id.howTrans)
        content2 = findViewById(R.id.content2)
        personalRegisterButton = findViewById(R.id.personalRegisterButton)


        //저장소 초기화
        storage = FirebaseStorage.getInstance()

        perImgPlus1.setOnClickListener {
            //앨범 열기
            var photoPickerIntent = Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
            photoPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); //사진을 여러장 선택할 수 있게 함
            photoPickerIntent.type = "image/*"
            photoPickerIntent.action = Intent.ACTION_GET_CONTENT;
            startActivityForResult(Intent.createChooser( photoPickerIntent, "Select Picture"), PICK_IMAGE_FROM_ALBUM)
        }
        perImgPlus2.setOnClickListener {
            //앨범 열기
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM2)
        }
        perImgPlus3.setOnClickListener {
            //앨범 열기
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM3)
        }
        perImgPlus4.setOnClickListener {
            //앨범 열기
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM4)
        }
        //등록 버튼 이벤트
        personalRegisterButton.setOnClickListener {
            val perTitle = perTitle.text.toString().trim()
            val product2 = product2.text.toString().trim()
            val methodTrans = methodTrans.text.toString().trim()
            val category2 = category2.text.toString().trim()
            val hopeArea = hopeArea.text.toString().trim()
            val howTrans = howTrans.text.toString().trim()
            val content2 = content2.text.toString().trim()

            writeNewPost(
                perTitle,
                product2,
                methodTrans,
                category2,
                hopeArea,
                howTrans,
                content2
            )
            contentUpload()
            val intent = Intent(this, PersonalHomeFragment::class.java)
            startActivity(intent)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == PICK_IMAGE_FROM_ALBUM) {
//            if (resultCode == Activity.RESULT_OK) {
//                //선택된 이미지 path
//                photoUri = data?.data
//                perImgPlus1.setImageURI(photoUri)
//            } else {
//                Toast.makeText(this@PersonalWriteActivity, "사진 4장을 선택해주세요", Toast.LENGTH_SHORT)
//                    .show()
//                return@onActivityResult
//            }
//        }
        try {
            // When an Image is picked
            if (requestCode ===  PICK_IMAGE_FROM_ALBUM && resultCode ===  Activity.RESULT_OK && null != attr.data) {
                // Get the Image from data
                val filePathColumn = arrayOf(MediaStore.Images.Media._ID)
               val imagesEncodedList = ArrayList<String>()
                if (attr.data.getData() != null) {
                    val mImageUri: Uri = attr.data.getData()

                    // Get the cursor
                    val cursor: Cursor? = contentResolver.query(
                        mImageUri,
                        filePathColumn, null, null, null
                    )
                    // Move to first row
                    cursor.moveToFirst()
                    val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
                    val imageEncoded = cursor.getString(columnIndex)
                    cursor.close()
                } else {
                    if (attr.data.getClipData() != null) {
                        val mClipData: ClipData = attr.data.getClipData()
                        val mArrayUri = ArrayList<Uri>()
                        for (i in 0 until mClipData.itemCount) {
                            val item = mClipData.getItemAt(i)
                            val uri = item.uri
                            mArrayUri.add(uri)
                            // Get the cursor
                            val cursor: Cursor? =
                                contentResolver.query(uri, filePathColumn, null, null, null)
                            // Move to first row
                            cursor.moveToFirst()
                            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
                            imageEncoded = cursor.getString(columnIndex)
                            imagesEncodedList.add(imageEncoded)
                            cursor.close()
                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size())
                    }
                }
            } else {
                Toast.makeText(
                    this, "You haven't picked Image",
                    Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                .show()
        }

    }

    @SuppressLint("SimpleDateFormat")
    private fun contentUpload() {
        //파일네임 만들기
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "IMAGE_" + timestamp + "_.png"

        val storageRef = storage?.reference?.child("images")?.child(imageFileName)

        //파일업로드
        storageRef?.putFile(photoUri!!)?.addOnSuccessListener {
            Toast.makeText(this@PersonalWriteActivity, "img_upload_success", Toast.LENGTH_SHORT)
                .show()
        }
    }


    private fun writeNewPost(
        perTitle: String,
        product2: String, methodTrans: String,
        category2: String, hopeArea: String,
        howTrans: String, content2: String
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
                content2
            )
            database.child("PostingData").child(key).setValue(newPost).addOnSuccessListener {
                Toast.makeText(
                    this@PersonalWriteActivity,
                    "posting_upload_success",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
    }
}