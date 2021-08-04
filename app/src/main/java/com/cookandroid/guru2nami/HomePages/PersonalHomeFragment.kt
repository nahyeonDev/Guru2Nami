package com.cookandroid.guru2nami.HomePages


import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Color.WHITE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.guru2nami.Adapters.ListPersonalAdapter
import com.cookandroid.guru2nami.Content.PersonalWriteActivity
import com.cookandroid.guru2nami.Content.TogetherWriteActivity
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Personal
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.common.base.Ascii.FF
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


//홈. 상단 탭(개인 나눔)을 눌렀을때 나오는 fragment
class PersonalHomeFragment : Fragment() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList : ArrayList<Personal>
    private lateinit var perView : View

    //플로팅버튼 애니메이션을 위한 변수
    lateinit var perBtn : FloatingActionButton
    lateinit var togBtn :FloatingActionButton
    lateinit var fabMain :FloatingActionButton
    private var isFabOpen = false

    //데이터
    private val _contact = MutableLiveData<Personal>()
    val contact: LiveData<Personal> get() = _contact
    lateinit var image1 : ImageView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //fragment_personal_home이 리사이클러뷰 쭉 있는 xml
        perView = inflater.inflate(R.layout.fragment_personal_home, container, false)

        userRecyclerView = perView.findViewById(R.id.recycler_per)
        userRecyclerView.layoutManager = LinearLayoutManager(activity);
        userRecyclerView.setHasFixedSize(true)

        perBtn = perView.findViewById(R.id.per_btn)
        togBtn = perView.findViewById(R.id.tog_btn)
        fabMain = perView.findViewById(R.id.fav_btn)

        userArrayList = arrayListOf<Personal>()

        getUserData()

        return perView
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        fabMain.setOnClickListener {
            toggleFab()
        }
        //나눔 버튼 클릭 시 개인나눔 글쓰기 화면으로 전환
        perBtn.setOnClickListener {
            val intent = Intent(activity, PersonalWriteActivity::class.java)
            startActivity(intent)
        }
        //공구 버튼 클릭 시 공구 글쓰기 화면으로 전환
        togBtn.setOnClickListener {
            val intent = Intent(activity, TogetherWriteActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getUserData(){

        dbref = FirebaseDatabase.getInstance().getReference("PostingData")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userArrayList.clear()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(Personal::class.java)
                        userArrayList.add(user!!)

                    }
                    userRecyclerView.adapter = ListPersonalAdapter(userArrayList)
                }
                userRecyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }


    private fun toggleFab(){
        //플로팅 액션 버튼 닫기/열기
        if(isFabOpen){
            ObjectAnimator.ofFloat(perBtn, "translationY", 0f).apply{start()}
            ObjectAnimator.ofFloat(togBtn, "translationY", 0f).apply{start()}
            fabMain.setImageResource(R.drawable.ic_baseline_create_24)
//            fabMain.setBackgroundColor(Color.WHITE)
        }else{
            ObjectAnimator.ofFloat(perBtn, "translationY", -200f).apply{start()}
            ObjectAnimator.ofFloat(togBtn, "translationY", -400f).apply{start()}
            fabMain.setImageResource(R.drawable.ic_baseline_remove_24)
        }
        isFabOpen = !isFabOpen
    }
}
