package com.cookandroid.guru2nami.Adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.cookandroid.guru2nami.Content.DetailViewActivity
import com.cookandroid.guru2nami.Content.PersonalWriteActivity
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.Personal
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.io.InputStream


//개인나눔화면 리스트뷰와 카드뷰 연결 어댑터
//Personal 은 개인나눔 화면 게시물정보(이미지 네장, 프로필사진, 글 등이 매개변수로 있음)

class ListPersonalAdapter(private val userList: ArrayList<Personal>) : RecyclerView.Adapter<ListPersonalAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPersonalAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_per_list,
                parent,
                false
        )

        val image_per = itemView.findViewById<ImageView>(R.id.image_per)

//        val intent = Intent.getIntentOld("imageFileName").toString()
//
//        val storage :FirebaseStorage = FirebaseStorage.getInstance()
        val storageRef : StorageReference = Firebase.storage.reference.child("images/"+PersonalWriteActivity().imageFileName)

        Glide.with(itemView).load(storageRef).into(image_per)

        return MyViewHolder(itemView).apply {
            itemView.setOnClickListener {
                val curPos : Int = adapterPosition  //curPos는 현재 클릭하는 포지션
                val personal: Personal = userList.get(curPos)  //userList는 Personal 클래스 안에 있는 변수 항목들
                val title = perTitle.text.toString()
                val area = hopeArea.text.toString()
                val content = content2.text.toString()
                val id = userName.text.toString()
                val ca = category2.text.toString()

                var intent = Intent(parent.context, DetailViewActivity::class.java)
                 intent.putExtra("Title", title) /*송신*/
                 intent.putExtra("hopeArea", area)
                 intent.putExtra("Content", content)
                 intent.putExtra("id", id)
                intent.putExtra("category", ca)

                parent.context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: ListPersonalAdapter.MyViewHolder, position: Int) {
        val currentitem = userList[position]

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
        val perTitle : TextView = itemView.findViewById(R.id.title_per)
        val hopeArea : TextView = itemView.findViewById(R.id.area_per)
        val content2 : TextView = itemView.findViewById(R.id.content_main)
        val userName : TextView = itemView.findViewById(R.id.id_per)
        val category2 : TextView = itemView.findViewById(R.id.category_per)

    }

}

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        // Register FirebaseImageLoader to handle StorageReference
        registry.append(StorageReference::class.java, InputStream::class.java,
                FirebaseImageLoader.Factory())
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}


