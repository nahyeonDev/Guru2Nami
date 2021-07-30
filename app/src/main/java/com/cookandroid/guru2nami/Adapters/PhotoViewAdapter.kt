package com.cookandroid.guru2nami.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

//상세페이지. activity_detailview.xml의 이미지에 fragment_photo를 연결시키는 Adapter

class PhotoViewAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val items = ArrayList<Fragment>()

    //position위치의 프래그먼트
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    fun updateFragments(items : List<Fragment>) {
        this.items.addAll(items)
    }
}