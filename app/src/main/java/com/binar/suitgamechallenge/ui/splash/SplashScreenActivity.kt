package com.binar.suitgamechallenge.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.binar.suitgamechallenge.databinding.ActivitySplashScreenBinding
import com.binar.suitgamechallenge.ui.intro.IntroActivity
import java.util.*

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    private val timer: CountDownTimer by lazy {
        object : CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                val intent = Intent(this@SplashScreenActivity, IntroActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setSplashTimer()
    }

    private fun setSplashTimer() {
        timer.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        //stop timer
        timer.cancel()
    }
}