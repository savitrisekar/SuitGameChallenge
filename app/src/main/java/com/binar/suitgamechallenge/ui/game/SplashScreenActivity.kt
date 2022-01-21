package com.binar.suitgamechallenge.ui.game

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.binar.suitgamechallenge.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        bindView()
    }

    private fun bindView() {
        Handler().postDelayed({
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}