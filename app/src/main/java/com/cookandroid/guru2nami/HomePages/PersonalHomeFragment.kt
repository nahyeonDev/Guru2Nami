package com.cookandroid.guru2nami.HomePages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.cookandroid.guru2nami.Fragment.HomeFragment
import com.cookandroid.guru2nami.R

//홈. 상단 탭(개인 나눔)을 눌렀을때 나오는 fragment
class PersonalHomeFragment : Fragment() {
    lateinit var btnOne : Button

    private val fragmentPersonal = HomeFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_home, container, false)
    }

}