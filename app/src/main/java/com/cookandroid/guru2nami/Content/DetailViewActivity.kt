package com.cookandroid.guru2nami.Content

import android.Manifest
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.cookandroid.guru2nami.*
import com.cookandroid.guru2nami.Adapters.PhotoViewAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.net.URL
import kotlin.concurrent.thread


//상세페이지
class DetailViewActivity : AppCompatActivity() {

    private val REQUEST_READ_EXTERNAL_STORAGE = 1000
    //lateinit var viewPager : ViewPager

    //lateinit var user : TextView
    lateinit var hopeArea : TextView
    lateinit var title : TextView
    lateinit var content : TextView
    lateinit var userId : TextView
    lateinit var category: TextView
    lateinit var imageMain : ImageView

    //버튼들
    lateinit var backBtn : ImageButton
    lateinit var goToChat : Button
    lateinit var okBtn : Button
    lateinit var likeBtn : Button
    //
    private var areaH : String? =null
    private var tit : String? =null
    private var cont : String? =null
    private var uId : String? = null
    private var urlImg : String? = null

    private lateinit var url : URL

    //firebase
    private lateinit var database: DatabaseReference
    private var mAuth: FirebaseAuth? = null
    lateinit var uid : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailview)

        backBtn = findViewById(R.id.backBtn)
        goToChat = findViewById(R.id.goToChat)
        okBtn = findViewById(R.id.okBtn)
        likeBtn = findViewById(R.id.likeIcon)

        hopeArea = findViewById(R.id.hopeArea)
        title = findViewById(R.id.title)
        content = findViewById(R.id.content)
        userId = findViewById(R.id.user_id)
        category = findViewById(R.id.category)
        imageMain = findViewById(R.id.imgViewMain)

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
           // getAllPhotos()
        }

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

        urlImg = intent.extras!!.getString("img")
        val imgUrl : String = "https://firebasestorage.googleapis.com/" +
                "v0/b/nami-market.appspot.com/o/images%2F"+ urlImg+
                "?alt=media&token=8770eebd-9052-4fe7-9e1a-a70273921fbf"

        setImage(imgUrl)


        database = Firebase.database.reference

        //채팅 버튼
        goToChat.setOnClickListener {
            posting()
            val intent = Intent(this, MainActivity::class.java)
            Toast.makeText(this@DetailViewActivity, "채팅에서 만나요 :)", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

        //주문 버튼
        okBtn.setOnClickListener {
            postBuying()
            val intent = Intent(this, MainActivity::class.java)
            Toast.makeText(this@DetailViewActivity, "주문 완료!", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

        //찜 버튼
        likeBtn.setOnClickListener {
            postLiking()
            Toast.makeText(this@DetailViewActivity, "찜\uD83D\uDC9C", Toast.LENGTH_LONG).show()
        }

        //뒤로가기 버튼
        backBtn.setOnClickListener{
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    //채팅으로 넘어감
    private fun posting() {
        val chatTitle= title.text.toString()
        val userId = userId.text.toString()

        makeNewPost(//글 업로드
            chatTitle,
            userId
        )
    }

    //주문내역으로 넘어감
    private fun postBuying() {
        val buyTitle= title.text.toString()

        makeBuyPost(//글 업로드
            buyTitle
        )
    }

    //찜 내역으로 넘어감
    private fun postLiking() {
        val likeTitle= title.text.toString()

        makeLikePost(//글 업로드
            likeTitle
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
        //viewPager.adapter = adapter
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

    private fun makeBuyPost(
        buyTitle : String
    ) {
        val key = database.child("Buy").push().key
        if (key == null) {
            Log.w(ContentValues.TAG, "Couldn't get push key for posts")
            return
        }
        val newPost = BuyPosting(
            buyTitle
        )
        database.child("Buy").child(key).setValue(newPost).addOnSuccessListener{

        }
    }

    private fun makeLikePost(
        likeTitle : String
    ) {
        val key = database.child("Like").push().key
        if (key == null) {
            Log.w(ContentValues.TAG, "Couldn't get push key for posts")
            return
        }
        val newPost = LikePosting(
            likeTitle
        )
        database.child("Like").child(key).setValue(newPost).addOnSuccessListener{

        }
    }

    private fun setImage(url:String) {
        // 네트워크로 이미지를 불러오기 위해 서브스레드 처리
        thread(start=true) {
            val urlConnection = URL(url)
            val connection = urlConnection.openConnection()
            connection.doInput = true
            connection.connect()
            val input = connection.getInputStream()
            val bitmap = BitmapFactory.decodeStream(input)
            // 불러온 이미지를 화면에 그리기 위해 메인스레드 처리
            runOnUiThread {
                imageMain.setImageBitmap(bitmap)
            }
        }
    }

}