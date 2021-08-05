package com.cookandroid.guru2nami.Adapters


import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.guru2nami.Content.DetailViewActivity
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Personal
import com.google.firebase.auth.ktx.actionCodeSettings
import org.w3c.dom.Text
import java.io.ByteArrayOutputStream


//개인나눔화면 리스트뷰와 카드뷰 연결 어댑터
//Personal 은 개인나눔 화면 게시물정보(이미지 네장, 프로필사진, 글 등이 매개변수로 있음)

class ListPersonalAdapter(private val userList: ArrayList<Personal>) : RecyclerView.Adapter<ListPersonalAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPersonalAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_per_list,
                parent,
                false
        )
        return MyViewHolder(itemView).apply {
            itemView.setOnClickListener {
                val curPos : Int = adapterPosition  //curPos는 현재 클릭하는 포지션
                val personal: Personal = userList.get(curPos)  //userList는 Personal 클래스 안에 있는 변수 항목들
//                Toast.makeText(parent.context, "희망 지역 : ${personal.perTitle}", Toast.LENGTH_SHORT).show()
                val title = perTitle.text.toString()
                val area = hopeArea.text.toString()
                val content = content2.text.toString()
                val id = userName.text.toString()
                val ca = category2.text.toString()
                val imgD = imgDetail.text.toString()

                var intent = Intent(parent.context, DetailViewActivity::class.java)
                 intent.putExtra("Title", title) /*송신*/
                 intent.putExtra("hopeArea", area)
                 intent.putExtra("Content", content)
                 intent.putExtra("id", id)
                intent.putExtra("category", ca)
                intent.putExtra("img",imgD)

                parent.context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: ListPersonalAdapter.MyViewHolder, position: Int) {
        val currentitem = userList[position]

        val imageName : String? = currentitem.image1 //저장된 이미지 이름 받아오기
        val imgUrl : String = "https://firebasestorage.googleapis.com/" +
                "v0/b/nami-market.appspot.com/o/images%2F"+ imageName +
                "?alt=media&token=8770eebd-9052-4fe7-9e1a-a70273921fbf" //이미지 url

        Glide.with(holder.itemView).load(imgUrl).into(holder.image2) //이미지 배치할 곳에 url 로드

        holder.imgDetail.text = currentitem.image1
        holder.perTitle.text = currentitem.perTitle
        holder.hopeArea.text = currentitem.hopeArea
        holder.content2.text = currentitem.content2
        holder.userName.text = currentitem.userName
        holder.category2.text = currentitem.category2

    }
    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgDetail : TextView = itemView.findViewById(R.id.imgDetail)
        val image2 : ImageView = itemView.findViewById(R.id.image_per)
        val perTitle : TextView = itemView.findViewById(R.id.title_per)
        val hopeArea : TextView = itemView.findViewById(R.id.area_per)
        val content2 : TextView = itemView.findViewById(R.id.content_main)
        val userName : TextView = itemView.findViewById(R.id.id_per)
        val category2 : TextView = itemView.findViewById(R.id.category_per)

    }

}



