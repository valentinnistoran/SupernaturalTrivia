package com.example.spntrivia.ui.trivia

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.spntrivia.R
import com.example.spntrivia.databinding.FragmentTriviaBinding
class TriviaFragment : Fragment() {

    private lateinit var binding: FragmentTriviaBinding
    private val triviaViewModel: TriviaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTriviaBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.triviaViewModel = triviaViewModel

        // Load questions when the fragment is created
        loadQuestions()

        return binding.root
    }

    private fun loadQuestions() {
        val sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.shared_preference_game_mode), Context.MODE_PRIVATE
        )
        val difficultyLevel = sharedPreferences.getInt(getString(R.string.game_mode_key), 1)
        triviaViewModel.loadQuestions(requireContext(), difficultyLevel)
    }
}