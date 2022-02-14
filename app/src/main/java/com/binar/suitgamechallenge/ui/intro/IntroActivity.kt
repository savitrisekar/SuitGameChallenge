package com.binar.suitgamechallenge.ui.intro

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.binar.suitgamechallenge.R
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroCustomLayoutFragment.Companion.newInstance

class IntroActivity : AppIntro2() {

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        isSkipButtonEnabled = false

        addSlide(newInstance(R.layout.layout_intro_play_with_player))
        addSlide(newInstance(R.layout.layout_intro_play_with_computer))
        addSlide(InputNameFragment())

        setProgressIndicator()
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)

        if (currentFragment is InputNameFragment){
            currentFragment.navigateToMenu()
        }
    }
}