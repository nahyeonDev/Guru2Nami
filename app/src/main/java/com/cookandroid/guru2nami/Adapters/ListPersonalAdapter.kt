package com.cookandroid.guru2nami.Adapters


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.guru2nami.Content.DetailViewActivity
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Personal
import com.google.firebase.auth.ktx.actionCodeSettings


//개인나눔화면 리스트뷰와 카드뷰 연결 어댑터
//Personal 은 개인나눔 화면 게시물정보(이미지 네장, 프로필사진, 글 등이 매개변수로 있음)

class ListPersonalAdapter(private val userList : ArrayList<Personal>) : RecyclerView.Adapter<ListPersonalAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPersonalAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_per_list,parent,false)
        return MyViewHolder(itemView).apply {
            itemView.setOnClickListener {
                val curPos : Int = adapterPosition  //curPos는 현재 클릭하는 포지션
                val personal: Personal = userList.get(curPos)  //userList는 Personal 클래스 안에 있는 변수 항목들
//                Toast.makeText(parent.context, "희망 지역 : ${personal.perTitle}", Toast.LENGTH_SHORT).show()

                var intent = Intent(parent.context, DetailViewActivity::class.java)
                parent.context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: ListPersonalAdapter.MyViewHolder, position: Int) {
        val currentitem = userList[position]

        holder.perTitle.text = currentitem.perTitle
        holder.hopeArea.text = currentitem.hopeArea
        holder.content2.text = currentitem.content2

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val perTitle : TextView = itemView.findViewById(R.id.title_per)
        val hopeArea : TextView = itemView.findViewById(R.id.area_per)
        val content2 : TextView = itemView.findViewById(R.id.content_main)
    }

}

