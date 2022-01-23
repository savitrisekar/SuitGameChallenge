package com.binar.suitgamechallenge.ui.game

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.binar.suitgamechallenge.R
import com.binar.suitgamechallenge.databinding.ActivityGameBinding
import com.binar.suitgamechallenge.enum.SuitCharacter
import com.binar.suitgamechallenge.enum.SuitCharacterSide
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private val TAG = GameActivity::class.simpleName

    private lateinit var binding: ActivityGameBinding

    private var playerPosition: Int = -1
    private var compPosition: Int = -1
    private var isGameFinished: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        setInitialState()
        setClickListener()
    }

    private fun bindView() {
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    private fun setInitialState() {
        selectPlayer(playerPosition, SuitCharacterSide.PLAYER)
        selectPlayer(compPosition, SuitCharacterSide.COMPUTER)

        binding.tvVs.text = getString(R.string.text_vs)
        binding.tvRestart.visibility = View.INVISIBLE
    }

    private fun setClickListener() {

        binding.ivPlayerRock.setOnClickListener {
            if (!isGameFinished) {
                playerPosition = 0
                startGame()
            }
        }

        binding.ivPlayerPaper.setOnClickListener {
            if (!isGameFinished) {
                playerPosition = 1
                startGame()
            }
        }

        binding.ivPlayerScissor.setOnClickListener {
            if (!isGameFinished) {
                playerPosition = 2
                startGame()
            }
        }

        binding.tvRestart.setOnClickListener {
            if (isGameFinished) {
                isGameFinished = false
                resetGame()
            }
        }
    }

    private fun startGame() {
        //random computer
        compPosition = Random.nextInt(0, 3)
        //set select player
        selectPlayer(playerPosition, SuitCharacterSide.PLAYER)
        Log.d(TAG, "player: $playerPosition")
        //set select computer
        selectPlayer(compPosition, SuitCharacterSide.COMPUTER)
        Log.d(TAG, "computer: $compPosition")

        //proceed winner
        when {
            playerPosition == compPosition -> {
                binding.tvVs.text = getString(R.string.text_draw)
            }
            (playerPosition + 1) % 3 == compPosition -> {
                binding.tvVs.text = getString(R.string.text_comp_win)
            }
            else -> {
                binding.tvVs.text = getString(R.string.text_player_win)
            }
        }

        isGameFinished = true
        binding.tvRestart.visibility = View.VISIBLE
    }

    private fun resetGame() {
        isGameFinished = false
        binding.tvVs.text = getString(R.string.text_vs)
        binding.tvRestart.visibility = View.INVISIBLE

        binding.ivPlayerRock.setBackgroundColor(0)
        binding.ivPlayerPaper.setBackgroundColor(0)
        binding.ivPlayerScissor.setBackgroundColor(0)

        binding.ivComputerRock.setBackgroundColor(0)
        binding.ivComputerPaper.setBackgroundColor(0)
        binding.ivComputerScissor.setBackgroundColor(0)
    }

    private fun selectPlayer(
        suitCharacter: Int,
        suitCharacterSide: SuitCharacterSide
    ) {
        val ivRock: ImageView?
        val ivPaper: ImageView?
        val ivScissor: ImageView?

        if (suitCharacterSide == SuitCharacterSide.PLAYER) {
            ivRock = binding.ivPlayerRock
            ivPaper = binding.ivPlayerPaper
            ivScissor = binding.ivPlayerScissor
        } else {
            ivRock = binding.ivComputerRock
            ivPaper = binding.ivComputerPaper
            ivScissor = binding.ivComputerScissor
        }

        when (SuitCharacter.getByValue(suitCharacter)) {
            SuitCharacter.ROCK -> {
                ivRock.setBackgroundResource(R.drawable.bg_select_character)
            }
            SuitCharacter.PAPER -> {
                ivPaper.setBackgroundResource(R.drawable.bg_select_character)
            }
            SuitCharacter.SCISSOR -> {
                ivScissor.setBackgroundResource(R.drawable.bg_select_character)
            }
        }
    }
}