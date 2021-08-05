package com.cookandroid.guru2nami.HomePages

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.guru2nami.Adapters.ListPersonalAdapter
import com.cookandroid.guru2nami.Adapters.ListTogetherAdapter
import com.cookandroid.guru2nami.Content.PersonalWriteActivity
import com.cookandroid.guru2nami.Content.TogetherWriteActivity
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Personal
import com.cookandroid.guru2nami.User.Together
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

//홈. 상단 탭(공구 이벤트)을 눌렀을때 나오는 fragment
class TogHomeFragment : Fragment() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList : ArrayList<Together>
    private lateinit var togView : View

    //플로팅버튼 애니메이션을 위한 변수
    lateinit var perBtn2 : FloatingActionButton
    lateinit var togBtn2 : FloatingActionButton
    lateinit var fabMain2 : FloatingActionButton
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
        togView = inflater.inflate(R.layout.fragment_tog_home, container, false)

        userRecyclerView = togView.findViewById(R.id.recycler_tog)
        userRecyclerView.layoutManager = LinearLayoutManager(activity);
        userRecyclerView.setHasFixedSize(true)

        perBtn2 = togView.findViewById(R.id.per_btn)
        togBtn2 = togView.findViewById(R.id.tog_btn)
        fabMain2 = togView.findViewById(R.id.fav_btn)

        userArrayList = arrayListOf<Together>()

        getUserData2()

        return togView
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        //플로팅 버튼 클릭 시 애니메이션 동작 기능
        fabMain2.setOnClickListener {
            toggleFab2()
        }
        //나눔 버튼 클릭 시 개인나눔 글쓰기 화면으로 전환
        perBtn2.setOnClickListener {
            val intent = Intent(activity, PersonalWriteActivity ::class.java)
            startActivity(intent)
        }
        //공구 버튼 클릭 시 공구 글쓰기 화면으로 전환
        togBtn2.setOnClickListener {
            val intent = Intent(activity, TogetherWriteActivity ::class.java)
            startActivity(intent)
        }
    }

    private fun getUserData2(){

        dbref = FirebaseDatabase.getInstance().getReference("PostingData2")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userArrayList.clear()
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(Together::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter =  ListTogetherAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun toggleFab2(){
        //플로팅 액션 버튼 닫기/열기
        if(isFabOpen){
            ObjectAnimator.ofFloat(perBtn2, "translationY",0f).apply{start()}
            ObjectAnimator.ofFloat(togBtn2, "translationY",0f).apply{start()}
            fabMain2.setImageResource(R.drawable.ic_baseline_create_24)
        }else{
            ObjectAnimator.ofFloat(perBtn2, "translationY",-200f).apply{start()}
            ObjectAnimator.ofFloat(togBtn2, "translationY",-400f).apply{start()}
            fabMain2.setImageResource(R.drawable.ic_baseline_remove_24)
        }
        isFabOpen = !isFabOpen
    }
}