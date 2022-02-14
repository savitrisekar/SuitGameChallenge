package com.binar.suitgamechallenge.ui.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.binar.suitgamechallenge.data.preference.UserPreference
import com.binar.suitgamechallenge.databinding.ActivityMenuGameBinding
import com.binar.suitgamechallenge.ui.game.GameActivity

class MenuGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setNameOnTitle()
        setMenuClickListener()
    }

    private fun setNameOnTitle() {
        binding.tvPlayer.text = UserPreference(this).name
        binding.tvPlayerOne.text = UserPreference(this).name
    }

    private fun setMenuClickListener() {

        binding.ivPlayPlayer.setOnClickListener {
            GameActivity.startActivity(this, GameActivity.GAMEPLAY_MODE_VS_PLAYER)
        }
        binding.ivPlayComputer.setOnClickListener {
            GameActivity.startActivity(this, GameActivity.GAMEPLAY_MODE_VS_COMPUTER)
        }
    }
}