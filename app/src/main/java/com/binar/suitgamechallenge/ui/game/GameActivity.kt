package com.binar.suitgamechallenge.ui.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.binar.suitgamechallenge.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_game)
        bindView()
    }

    private fun bindView() {
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}