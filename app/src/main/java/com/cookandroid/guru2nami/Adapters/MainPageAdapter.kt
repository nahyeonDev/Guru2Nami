package com.cookandroid.guru2nami.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cookandroid.guru2nami.Fragment.*

//메인페이지에서 하단네비게이션바를 눌렀을 때, 뷰페이저에 나타나는 fragment를 연결하기 위한 Adapter

class MainPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()
            1 -> RankFragment()
            2 -> MapFragment()
            3 ->ChatFragment()
            else -> MyPageFragment()
        }
    }
    override fun getCount() = 5
}