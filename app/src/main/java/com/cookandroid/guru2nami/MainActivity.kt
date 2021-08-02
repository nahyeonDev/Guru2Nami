package com.cookandroid.guru2nami

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.viewpager.widget.ViewPager
import com.cookandroid.guru2nami.Adapters.MainPageAdapter
import com.cookandroid.guru2nami.MapMenu.MapActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(){

    lateinit var bottomNav : BottomNavigationView //하단메뉴바
    lateinit var viewContainer : ViewPager //하단메뉴바로 바뀌는 화면

    lateinit var mapBtn : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1) //사진 접근 권한

        mapBtn = findViewById(R.id.mapMenu)

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
                //네비게이션에 지도 메뉴는 버튼으로 넣었기 때문에 지도 버튼을 위치할 공간을 만들기 위해 설정됨
                R.id.menu_map -> viewContainer.currentItem = 2
                R.id.menu_chat -> viewContainer.currentItem = 3
                R.id.menu_myPage -> viewContainer.currentItem = 4
            }
            true
        }

        mapBtn.setOnClickListener {
            val intent = Intent(this, MapActivity ::class.java)
            startActivity(intent)
        }

    }

}

