package com.binar.suitgamechallenge.ui.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.binar.suitgamechallenge.R
import com.binar.suitgamechallenge.data.enum.SuitCharacter
import com.binar.suitgamechallenge.data.enum.SuitCharacterSide
import com.binar.suitgamechallenge.data.preference.UserPreference
import com.binar.suitgamechallenge.databinding.ActivityGameBinding
import com.binar.suitgamechallenge.ui.menu.MenuGameActivity
import com.binar.suitgamechallenge.utils.DialogUtils
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private val TAG = GameActivity::class.simpleName

    private lateinit var binding: ActivityGameBinding

    private var playerOnePosition: Int = -1
    private var playerTwoPosition: Int = -1
    private var isGameFinished: Boolean = false

    private var gamePlayMode: Int = GAMEPLAY_MODE_VS_COMPUTER
    private var playTurn: SuitCharacterSide = SuitCharacterSide.PLAYER

    companion object {
        private const val EXTRAS_GAME_MODE = "EXTRAS_GAME_MODE"
        const val GAMEPLAY_MODE_VS_COMPUTER = 0
        const val GAMEPLAY_MODE_VS_PLAYER = 1

        fun startActivity(context: Context, gameplayMode: Int) {
            val intent = Intent(context, GameActivity::class.java)
            intent.putExtra(EXTRAS_GAME_MODE, gameplayMode)
            context.startActivity(intent)
        }
    }

    private fun getIntentData() {
        gamePlayMode = intent.getIntExtra(EXTRAS_GAME_MODE, gamePlayMode)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        getIntentData()
        setPlayerName()
        setInitialState()
        setClickListener()
    }

    private fun showUIPlayer(suitCharacterSide: SuitCharacterSide, isVisible: Boolean) {
        if (suitCharacterSide == SuitCharacterSide.PLAYER) {
            binding.llPlayerOne.isVisible = isVisible
        } else {
            binding.llPlayerTwo.isVisible = isVisible
        }
    }

    private fun bindView() {
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    private fun setPlayerName() {
        binding.tvPlayerOne.text = UserPreference(this).name
        if (gamePlayMode == GAMEPLAY_MODE_VS_PLAYER) {
            binding.tvPlayerTwo.text = getString(R.string.text_player_two)
        } else {
            binding.tvPlayerTwo.text = getString(R.string.text_computer)
        }
    }

    private fun setInitialState() {
        selectPlayer(playerOnePosition, SuitCharacterSide.PLAYER)
        selectPlayer(playerTwoPosition, SuitCharacterSide.ENEMY)
        binding.tvVs.text = getString(R.string.text_vs)
        binding.btnRestart.visibility = View.INVISIBLE
        //check game mode
        if (gamePlayMode == GAMEPLAY_MODE_VS_PLAYER) {
            Toast.makeText(this, getString(R.string.text_player_turn), Toast.LENGTH_SHORT).show()
            showUIPlayer(SuitCharacterSide.PLAYER, true)
            showUIPlayer(SuitCharacterSide.ENEMY, false)
            //show lock player 1
            binding.tvVs.text = getString(R.string.text_lock_player_one)
        } else {
            binding.tvVs.text = getString(R.string.text_vs)
        }
    }

    private fun setClickListener() {
        binding.ivPlayerRock.setOnClickListener {
            if (!isGameFinished) {
                if (playTurn == SuitCharacterSide.PLAYER) {
                    playerOnePosition = 0
                    selectPlayer(playerOnePosition, SuitCharacterSide.PLAYER)
                    Log.d(TAG, "playerOnePosition $playerOnePosition")

                    if (gamePlayMode == GAMEPLAY_MODE_VS_COMPUTER) {
                        startGame()
                    } else {
                        binding.tvVs.visibility = View.VISIBLE
                    }
                } else {
                    playerTwoPosition = 0
                    selectPlayer(playerTwoPosition, SuitCharacterSide.PLAYER)
                    Log.d(TAG, "playerTwoPosition $playerTwoPosition")
                    binding.tvVs.visibility = View.VISIBLE
                }
            }
        }

        binding.ivPlayerPaper.setOnClickListener {
            if (!isGameFinished) {
                if (playTurn == SuitCharacterSide.PLAYER) {
                    playerOnePosition = 1
                    selectPlayer(playerOnePosition, SuitCharacterSide.PLAYER)
                    Log.d(TAG, "playerOnePosition $playerOnePosition")

                    if (gamePlayMode == GAMEPLAY_MODE_VS_COMPUTER) {
                        startGame()
                    } else {
                        binding.tvVs.visibility = View.VISIBLE
                    }
                } else {
                    playerTwoPosition = 1
                    selectPlayer(playerTwoPosition, SuitCharacterSide.PLAYER)
                    Log.d(TAG, "playerTwoPosition $playerTwoPosition")
                    binding.tvVs.visibility = View.VISIBLE
                }
            }
        }

        binding.ivPlayerScissor.setOnClickListener {
            if (!isGameFinished) {
                if (playTurn == SuitCharacterSide.PLAYER) {
                    playerOnePosition = 2
                    selectPlayer(playerOnePosition, SuitCharacterSide.PLAYER)
                    Log.d(TAG, "playerOnePosition $playerOnePosition")

                    if (gamePlayMode == GAMEPLAY_MODE_VS_COMPUTER) {
                        startGame()
                    } else {
                        binding.tvVs.visibility = View.VISIBLE
                    }
                } else {
                    playerTwoPosition = 2
                    selectPlayer(playerTwoPosition, SuitCharacterSide.PLAYER)
                    Log.d(TAG, "playerTwoPosition $playerTwoPosition")
                    binding.tvVs.visibility = View.VISIBLE
                }
            }
        }

        binding.btnRestart.setOnClickListener {
            Log.d(TAG, "click")
            if (isGameFinished) {
                isGameFinished = false
                resetGame()
            }
        }

        binding.tvVs.setOnClickListener {
            if (playTurn == SuitCharacterSide.PLAYER) {
                playTurn = SuitCharacterSide.ENEMY
                selectPlayer(playerTwoPosition, SuitCharacterSide.PLAYER)
                binding.tvVs.visibility = View.INVISIBLE
                Toast.makeText(this, getString(R.string.text_lock_player_one), Toast.LENGTH_SHORT)
                    .show()
            } else {
                startGame()
            }
        }
    }

    private fun startGame() {
        if (gamePlayMode == GAMEPLAY_MODE_VS_COMPUTER) {
            //random computer
            playerTwoPosition = Random.nextInt(0, 3)
            //set select computer
            selectPlayer(playerTwoPosition, SuitCharacterSide.ENEMY)
            Log.d(TAG, "player two: $playerTwoPosition")
            binding.btnRestart.visibility = View.VISIBLE
        }

        //proceed winner
        when {
            playerOnePosition == playerTwoPosition -> {
                showCustomDialog(getString(R.string.text_draw))
            }
            (playerOnePosition + 1) % 3 == playerTwoPosition -> {
                showCustomDialog(getString(R.string.text_comp_win))
            }
            else -> {
                showCustomDialog(getString(R.string.text_player_win))
            }
        }
        isGameFinished = true
    }

    private fun resetGame() {
        if (isGameFinished) {
            isGameFinished = false
            playerOnePosition = -1
            playerTwoPosition = -1
            Log.d(TAG, "Game Restarted")
            setInitialState()
        }
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
                ivPaper.setBackgroundResource(0)
                ivScissor.setBackgroundResource(0)
            }
            SuitCharacter.PAPER -> {
                ivRock.setBackgroundResource(0)
                ivPaper.setBackgroundResource(R.drawable.bg_select_character)
                ivScissor.setBackgroundResource(0)
            }
            SuitCharacter.SCISSOR -> {
                ivRock.setBackgroundResource(0)
                ivPaper.setBackgroundResource(0)
                ivScissor.setBackgroundResource(R.drawable.bg_select_character)
            }
        }
    }

    private fun showCustomDialog(result: String) {
        DialogUtils.showResultDialog(this, result) { dialog, value ->
            if (value == "menu") {
                navigateToMenu()
            } else {
                resetGame()
            }
            Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
            dialog?.dismiss()
        }
    }

    private fun navigateToMenu() {
        val intent = Intent(this, MenuGameActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}