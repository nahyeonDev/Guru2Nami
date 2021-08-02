package com.cookandroid.guru2nami.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cookandroid.guru2nami.Fragment.*
import com.cookandroid.guru2nami.MainActivity

//메인페이지에서 하단네비게이션바를 눌렀을 때, 뷰페이저에 나타나는 fragment를 연결하기 위한 Adapter

class MainPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()
            1 -> RankFragment()
            //지도 버튼 공간 생성을 위한 버튼이기 때문에 홈프래그먼트로 임의로 연결함
            2 -> HomeFragment()
            3 -> ChatFragment()
            else -> MyPageFragment()
        }
    }
    override fun getCount() = 5
}