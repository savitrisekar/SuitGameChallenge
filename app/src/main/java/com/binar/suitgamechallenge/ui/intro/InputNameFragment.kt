package com.binar.suitgamechallenge.ui.intro

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.binar.suitgamechallenge.R
import com.binar.suitgamechallenge.databinding.FragmentInputNameBinding
import com.binar.suitgamechallenge.data.preference.UserPreference
import com.binar.suitgamechallenge.ui.menu.MenuGameActivity
import com.google.android.material.snackbar.Snackbar

class InputNameFragment : Fragment() {

    private lateinit var binding: FragmentInputNameBinding
    private val userPreference: UserPreference? by lazy {
        context?.let { UserPreference(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_input_name, container, false)
        binding = FragmentInputNameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPrefillName()
    }

    private fun setPrefillName() {
        userPreference?.let {
            binding.tieInputName.setText(it.name.orEmpty())
        }
    }

    public fun navigateToMenu() {
        if (isNameFilled()) {
            userPreference?.name = binding.tieInputName.text.toString().trim()
//            Toast.makeText(context, userPreference?.name, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, MenuGameActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun isNameFilled(): Boolean {
        val name = binding.tieInputName.text.toString().trim()
        var isFormValid = true

        if (name.isEmpty()) {
            isFormValid = false
            Snackbar.make(
                binding.root,
                getString(R.string.text_blank),
                Snackbar.LENGTH_SHORT
            ).show()
        }
        return isFormValid
    }
}