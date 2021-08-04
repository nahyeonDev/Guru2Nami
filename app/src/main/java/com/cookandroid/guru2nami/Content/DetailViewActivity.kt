package com.cookandroid.guru2nami.Content

import android.Manifest
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.cookandroid.guru2nami.Adapters.PhotoViewAdapter
import com.cookandroid.guru2nami.ChatPosting
import com.cookandroid.guru2nami.MainActivity
import com.cookandroid.guru2nami.MypageContent.SalesHistoryActivity
import com.cookandroid.guru2nami.PostingData
import com.cookandroid.guru2nami.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import org.w3c.dom.Text


//상세페이지
class DetailViewActivity : AppCompatActivity() {

    private val REQUEST_READ_EXTERNAL_STORAGE = 1000
    lateinit var viewPager : ViewPager

    //lateinit var user : TextView
    lateinit var hopeArea : TextView
    lateinit var title : TextView
    lateinit var content : TextView
    lateinit var userId : TextView

    //
    lateinit var goToChat : Button
    lateinit var okBtn : Button
    private var areaH : String? =null
    private var tit : String? =null
    private var cont : String? =null
    private var uId : String? = null

    //firebase
    private lateinit var database: DatabaseReference
    private var mAuth: FirebaseAuth? = null
    lateinit var uid : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailview)

        viewPager = findViewById(R.id.viewPager)

        //권한이 부여되었는지 확인
        if(ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)

        //권한이 허용되지 않음
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //이전에 거부한 적이 있으면 설명(경고)
                var dlg = AlertDialog.Builder(this)
                dlg.setTitle("권한이 필요한 이유")
                dlg.setMessage("사진 정보를 얻기 위해서는 외부 저장소 권한이 필수로 필요합니다.")
                dlg.setPositiveButton("확인"){ dialog, which -> ActivityCompat.requestPermissions(this@DetailViewActivity,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)}
                dlg.setNegativeButton("취소", null)
                dlg.show()
            }else {
                //처음 권한 요청
                ActivityCompat.requestPermissions(this@DetailViewActivity,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
            } else {
            //권한이 이미 제대로허용됨
            getAllPhotos()
        }
        goToChat = findViewById(R.id.goToChat)
        okBtn = findViewById(R.id.okBtn)

        hopeArea = findViewById(R.id.hopeArea)
        title = findViewById(R.id.title)
        content = findViewById(R.id.content)
        userId = findViewById(R.id.user_id)

        //화면 넘어오면서 정보도 같이 넘어오게 함
        val intent = intent /*데이터 수신*/

        areaH = intent.extras!!.getString("hopeArea") /*String형*/
        hopeArea.text = areaH

        tit = intent.extras!!.getString("Title")
        title.text = tit

        cont = intent.extras!!.getString("Content")
        content.text = cont

        uId = intent.extras!!.getString("id")
        userId.text = uId

        database = Firebase.database.reference

        //채팅 버튼
        goToChat.setOnClickListener {
            posting()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    private fun posting() {
        val chatTitle= title.text.toString()
        val userId = userId.text.toString()

        makeNewPost(//글 업로드
                chatTitle,
                userId
        )
    }

    private fun getAllPhotos() {
        val cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,  //가져올 항목 배열
                null,  //조건
                null,  //조건
                MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC") //날짜순

        val fragments = ArrayList<Fragment>()

        if(cursor != null) {
            while(cursor.moveToNext()){
                //사진 경로 Uri 가져오기
                val uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                Log.d("MainActivity", uri)
                fragments.add(PhotoFragment.newInstance(uri))
            }
            cursor.close()
        }

        val adapter = PhotoViewAdapter(supportFragmentManager)
        adapter.updateFragments(fragments)
        viewPager.adapter = adapter
    }

    private fun makeNewPost(
            chatTitle : String,
            userId : String
    ) {
        val key = database.child("Chat").push().key
        if (key == null) {
            Log.w(ContentValues.TAG, "Couldn't get push key for posts")
            return
        }
        val newPost = ChatPosting(
                chatTitle,
                userId

        )
        database.child("Chat").child(key).setValue(newPost).addOnSuccessListener{

        }
    }


}