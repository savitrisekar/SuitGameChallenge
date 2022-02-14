package com.binar.suitgamechallenge.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.binar.suitgamechallenge.databinding.DialogGameResultBinding

object DialogUtils {
    fun showResultDialog(
        context: Context,
        result: String,
        onButtonClick: (AlertDialog?, String) -> (Unit)
    ) {
        var dialog: AlertDialog? = null
        val dialogBinding =
            DialogGameResultBinding.inflate((context as AppCompatActivity).layoutInflater).apply {
                tvGameResult.text = result
                btnPlayAgain.setOnClickListener {
                    onButtonClick(dialog, "play_again")
                }
                btnMainMenu.setOnClickListener {
                    onButtonClick(dialog, "menu")
                }
            }
        dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .create()
        dialog.show()
    }
}