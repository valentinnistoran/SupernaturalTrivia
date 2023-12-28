package com.example.spntrivia.ui.trivia

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spntrivia.R
import com.example.spntrivia.databinding.FragmentTriviaBinding

class TriviaFragment : Fragment() {

    private lateinit var binding: FragmentTriviaBinding
    private val triviaViewModel: TriviaViewModel by viewModels()

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTriviaBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.triviaViewModel = triviaViewModel

        sharedPreferences = activity!!.getSharedPreferences(
            getString(R.string.shared_preference_game_mode), Context.MODE_PRIVATE
        )
        val difficultyMode =
            getDifficultyModeFromSharedPreferences(getString(R.string.game_mode_key))

        triviaViewModel.loadRandomQuestion(difficultyMode)

        return binding.root
    }

    private fun getDifficultyModeFromSharedPreferences(key: String): String {
        return sharedPreferences.getInt(key, 1).toString()
    }
}
