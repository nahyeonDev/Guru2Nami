package com.cookandroid.guru2nami.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cookandroid.guru2nami.Fragment.*
import com.cookandroid.guru2nami.HomePages.PersonalHomeFragment
import com.cookandroid.guru2nami.HomePages.TogHomeFragment

//홈 화면에서 상단 메뉴(개인,공구)를 눌렀을 때, 뷰페이저에 나타나는 fragment를 연결하기 위한 Adapter

class HomePageAdapter(manager: FragmentManager): FragmentPagerAdapter(manager){

    var fragmentList: MutableList<Fragment> = arrayListOf()
    var titleList: MutableList<String> = arrayListOf()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }


    fun addFragment(fragment: Fragment, title: String){
        fragmentList.add(fragment)
        titleList.add(title)
    }
}