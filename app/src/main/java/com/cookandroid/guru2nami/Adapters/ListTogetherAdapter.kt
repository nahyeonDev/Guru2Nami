package com.cookandroid.guru2nami.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.guru2nami.Content.DetailViewActivity
import com.cookandroid.guru2nami.Content.DetailViewActivity2
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Personal
import com.cookandroid.guru2nami.User.Together
import org.w3c.dom.Text

//공구화면 리스트뷰와 카드뷰 연결 어댑터

class ListTogetherAdapter (private val togList : ArrayList<Together>) : RecyclerView.Adapter<ListTogetherAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTogetherAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_tog_list,
            parent,
            false
        )
        return MyViewHolder(itemView).apply {
            itemView.setOnClickListener {
                val curPos : Int = adapterPosition  //curPos는 현재 클릭하는 포지션
                val together: Together = togList.get(curPos)  //userList는 Personal 클래스 안에 있는 변수 항목들
                val title = togTitle.text.toString()
                val area = hopeArea2.text.toString()
                val content = content3.text.toString()
                val id = userName2.text.toString()
                val cate = category.text.toString()
                val imgD = imgDetail2.text.toString()

                var intent = Intent(parent.context, DetailViewActivity2::class.java)
                intent.putExtra("Title", title) /*송신*/
                intent.putExtra("hopeArea", area)
                intent.putExtra("Content", content)
                intent.putExtra("id", id)
                intent.putExtra("category", cate)
                intent.putExtra("img",imgD)

                parent.context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: ListTogetherAdapter.MyViewHolder, position: Int) {
        val currentitem = togList[position]

        val imageName2 : String? = currentitem.image2 //저장된 이미지 이름 받아오기
        val imgUrl2 : String = "https://firebasestorage.googleapis.com/" +
                "v0/b/nami-market.appspot.com/o/images%2F"+ imageName2 +
                "?alt=media&token=8770eebd-9052-4fe7-9e1a-a70273921fbf" //이미지 url

        Glide.with(holder.itemView).load(imgUrl2).into(holder.image3) //이미지 배치할 곳에 url 로드

        holder.imgDetail2.text = currentitem.image2
        holder.togTitle.text = currentitem.togTitle
        holder.hopeArea2.text = currentitem.hopeArea2
        holder.content3.text = currentitem.content3
        holder.userName2.text = currentitem.uid
        holder.category.text = currentitem.category

    }

    override fun getItemCount(): Int {
        return togList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val imgDetail2 : TextView = itemView.findViewById(R.id.imgDetail2)
        val image3 : ImageView = itemView.findViewById(R.id.image_tog)
        val togTitle : TextView = itemView.findViewById(R.id.title_tog)
        val hopeArea2 : TextView = itemView.findViewById(R.id.area_tog)
        val content3 : TextView = itemView.findViewById(R.id.content_main2)
        val userName2 : TextView = itemView.findViewById(R.id.id_tog)
        val category : TextView = itemView.findViewById(R.id.category_tog)
    }


}