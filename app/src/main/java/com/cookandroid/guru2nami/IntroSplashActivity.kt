package com.cookandroid.guru2nami

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.guru2nami.User.LoginActivity

//처음 인트로

class IntroSplashActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_splash)

        var handler = Handler()
        handler.postDelayed({var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }, 1000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}