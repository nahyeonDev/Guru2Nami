package com.cookandroid.guru2nami.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.cookandroid.guru2nami.Adapters.HomePageAdapter
import com.cookandroid.guru2nami.HomePages.PersonalHomeFragment
import com.cookandroid.guru2nami.HomePages.TogHomeFragment
import com.cookandroid.guru2nami.MainActivity
import com.cookandroid.guru2nami.R
import com.google.android.material.tabs.TabLayout

//홈 화면(게시물 화면)
/* 어댑터 사용해 상단 탭을 눌렀을 때 하단 뷰페이저에 프래그먼트 배치*/
class HomeFragment : Fragment() {

    lateinit var myFragment: View
    lateinit var viewHomePager: ViewPager //게시물이 배치되는 화면
    lateinit var topTabLayout: TabLayout //상단메뉴탭

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_home, container, false)
        viewHomePager = myFragment.findViewById(R.id.view_home)
        topTabLayout = myFragment.findViewById(R.id.tab_layout)
        return myFragment

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpViewPager()
        topTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setUpViewPager() {

        var adapter = HomePageAdapter(childFragmentManager)
        adapter.addFragment(PersonalHomeFragment(), "개인 나눔")
        adapter.addFragment(TogHomeFragment(), "공구 이벤트")

        viewHomePager!!.adapter = adapter
        topTabLayout!!.setupWithViewPager(viewHomePager)
    }

    companion object {

    }
}