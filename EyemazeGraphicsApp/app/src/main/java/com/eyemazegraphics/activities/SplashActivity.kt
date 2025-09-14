package com.eyemazegraphics.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.eyemazegraphics.R
import com.eyemazegraphics.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.logoImage.startAnimation(fadeIn)

        Handler(Looper.getMainLooper()).postDelayed({
            val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            binding.splashLayout.startAnimation(fadeOut)
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 1500) // Corresponds to fade_out duration
        }, 3000) // 3 second delay
    }
}
