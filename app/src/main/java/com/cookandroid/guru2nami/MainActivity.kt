package com.cookandroid.guru2nami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.cookandroid.guru2nami.Adapters.HomePageAdapter
import com.cookandroid.guru2nami.Adapters.MainPageAdapter
import com.cookandroid.guru2nami.HomePages.PersonalHomeFragment
import com.cookandroid.guru2nami.HomePages.TogHomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(){

    lateinit var bottomNav : BottomNavigationView //하단메뉴바
    lateinit var viewContainer : ViewPager //하단메뉴바로 바뀌는 화면

    lateinit var topNav : BottomNavigationView //상단메뉴바
    lateinit var viewHomeContainer : ViewPager //상단메뉴바로 바뀌는 화면

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_mainNav)
        viewContainer = findViewById(R.id.view_container)

        viewContainer.adapter = MainPageAdapter(supportFragmentManager)
        viewContainer.offscreenPageLimit = 5 //뷰 계층 구조에 보관된 페이지, view/fragment 수 제어

        viewContainer.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                // 네비게이션 메뉴 아이템 체크상태
                bottomNav.menu.getItem(position).isChecked = true
            }
        })

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                // itemId에 따라 viewPager 바뀜
                R.id.menu_home -> viewContainer.currentItem = 0
                R.id.menu_rank -> viewContainer.currentItem = 1
                R.id.menu_map -> viewContainer.currentItem = 2
                R.id.menu_chat -> viewContainer.currentItem = 3
                R.id.menu_myPage -> viewContainer.currentItem = 4
            }
            true
        }
    }
}

