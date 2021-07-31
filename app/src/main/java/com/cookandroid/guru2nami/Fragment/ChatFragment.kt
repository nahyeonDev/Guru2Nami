package com.cookandroid.guru2nami.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.cookandroid.guru2nami.Adapters.ListAdapter
import com.cookandroid.guru2nami.R
import com.cookandroid.guru2nami.User.User

//채팅 화면
//리스트뷰와 카드뷰를 연결함
class ChatFragment : Fragment() {

    lateinit var listView : ListView
    lateinit var chatView : View

    var UserList = arrayListOf<User>(
            User(R.drawable.ic_launcher_background,"nahyeonDev","1",
                    "공구 관련 문의사항 있어서 채팅보냅니다!"),
            User(R.drawable.ic_launcher_background,"nahyeonDev","2",
                    "공구 관련 문의사항 있어서 채팅보냅니다!"),
            User(R.drawable.ic_launcher_background,"nahyeonDev","3",
                    "공구 관련 문의사항 있어서 채팅보냅니다!"),
            User(R.drawable.ic_launcher_background,"nahyeonDev","4",
                    "공구 관련 문의사항 있어서 채팅보냅니다!"),
            User(R.drawable.ic_launcher_background,"nahyeonDev","5",
                    "공구 관련 문의사항 있어서 채팅보냅니다!"),

    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        chatView =  inflater.inflate(R.layout.fragment_chat, container, false)
        listView = chatView.findViewById(R.id.list_view)
        return chatView
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        listView.apply {

            var Adapter = ListAdapter(context, UserList)
            listView.adapter = Adapter
        }
    }
}