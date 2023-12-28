package com.example.spntrivia.ui.trivia

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.spntrivia.R
import com.example.spntrivia.databinding.FragmentTriviaBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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

        loadQuestions()
        quitButtonObserver()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showQuitDialog()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun loadQuestions() {
        val sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.shared_preference_game_mode), Context.MODE_PRIVATE
        )
        val difficultyLevel = sharedPreferences.getInt(getString(R.string.game_mode_key), 1)
        triviaViewModel.loadQuestions(requireContext(), difficultyLevel)
    }

    private fun showQuitDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Quit Quiz")
            .setMessage("Are you sure you want to quit the quiz?")
            .setPositiveButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Yes") { _, _ ->
                findNavController().popBackStack(R.id.navigation_home, false)
            }
            .show()
    }

    private fun quitButtonObserver() {
        triviaViewModel.onQuitButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                showQuitDialog()
                triviaViewModel.onQuitButtonClicked.value = false
            }
        }
    }


}