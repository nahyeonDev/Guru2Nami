package com.cookandroid.guru2nami.MapMenu

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.guru2nami.R
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.overlay.OverlayImage

//지도 화면

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mapView: MapView? = null
    private var myMap: NaverMap? = null

    //마커 변수 선언 및 초기화
    private val marker1 = Marker()
    private val marker2 = Marker()
    private val marker3 = Marker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        //나눔,공구 버튼 연결
        val btnMark1 = findViewById<View>(R.id.btnMark1) as Button
        val btnMark2 = findViewById<View>(R.id.btnMark2) as Button

        //나눔 버튼 클릭 시 마커 등장
        btnMark1.setOnClickListener {
            setMarker(marker2, 37.6204, 127.0837, R.drawable.marker, 0)
            marker2.onClickListener = Overlay.OnClickListener {
                Toast.makeText(application, "나눔1 클릭", Toast.LENGTH_SHORT).show()
                false
            }
        }
        //공구 버튼 클릭 시 마커 등장
        btnMark2.setOnClickListener {
            setMarker(marker3, 37.623523383230214, 127.08520577804737, R.drawable.marker2, 10)
            marker3.onClickListener = Overlay.OnClickListener {
                Toast.makeText(application, "공구1 클릭", Toast.LENGTH_SHORT).show()
                false
            }

        }

        //네이버 지도
        mapView = findViewById<View>(R.id.map_main) as MapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this)
    }

    //마커 만드는 함수
    private fun setMarker(marker: Marker, lat: Double, lng: Double, resourceID: Int, zIndex: Int) {
        //원근감 표시
        marker.isIconPerspectiveEnabled = true
        //아이콘 지정
        marker.icon = OverlayImage.fromResource(resourceID)
        //마커의 투명도
        marker.alpha = 0.8f
        //마커 위치
        marker.position = LatLng(lat, lng)
        //마커 우선순위
        marker.zIndex = zIndex
        //마커 표시
        marker.map = myMap
    }

    //지도 설정
    override fun onMapReady(myMap: NaverMap) {
        this.myMap = myMap

        //배경 지도 선택
        myMap.mapType = NaverMap.MapType.Navi

        //건물 표시
        myMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING, true)

        //위치 및 각도 조정
        val cameraPosition = CameraPosition(
            LatLng(37.6204, 127.0837),  // 위치 지정
            15.0,  // 줌 레벨
            0.0,  // 기울임 각도
            0.0// 방향
        )
        myMap.cameraPosition = cameraPosition
    }

    override fun onStart() {
        super.onStart()
        mapView!!.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView!!.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView!!.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView!!.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState!!)
        mapView!!.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }
}