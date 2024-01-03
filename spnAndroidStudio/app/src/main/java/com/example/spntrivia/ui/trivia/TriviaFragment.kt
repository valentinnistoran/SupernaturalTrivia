package com.example.spntrivia.ui.trivia

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.spntrivia.R
import com.example.spntrivia.databinding.FragmentTriviaBinding
import com.example.spntrivia.gameHistoryDB.QuizResult
import com.example.spntrivia.gameHistoryDB.QuizResultsViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TriviaFragment : Fragment() {

    private lateinit var binding: FragmentTriviaBinding
    private val triviaViewModel: TriviaViewModel by viewModels()
    private lateinit var quizResultsViewModel: QuizResultsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTriviaBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.triviaViewModel = triviaViewModel

        quizResultsViewModel = ViewModelProvider(this).get(QuizResultsViewModel::class.java)

        loadQuestions()
        questionsAnsweredObserver()
        quitButtonObserver()
        skipButtonObserver()
        nextButtonObserver()
        answer1ButtonObserver()
        answer2ButtonObserver()
        answer3ButtonObserver()
        answer4ButtonObserver()
        timerObserver()
        timerFinishedObserver()

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

    private fun questionsAnsweredObserver() {
        triviaViewModel.questionsAnswered.observe(viewLifecycleOwner) { answeredCount ->
            Log.d("TriviaFragment", "Answered question count: $answeredCount")

            binding.quizNumber.text = Integer.toString(answeredCount)
            binding.executePendingBindings()
        }
    }

    private fun loadQuestions() {
        val sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.shared_preference_game_mode), Context.MODE_PRIVATE
        )
        val difficultyLevel = sharedPreferences.getInt(getString(R.string.game_mode_key), 1)
        triviaViewModel.loadQuestions(requireContext(), difficultyLevel)
        triviaViewModel.timer.start()
    }

    private fun showQuitDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Quit Quiz")
            .setMessage("Are you sure you want to quit the quiz?")
            .setPositiveButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Yes") { _, _ ->
                triviaViewModel.confirmQuit()
                triviaViewModel.timer.cancel()
                findNavController().popBackStack(R.id.navigation_home, false)
            }
            .show()
    }

    private fun navigateToEndQuiz() {
        if (triviaViewModel.questionsAnswered.value == 0) {
            insertDataToDatabase()
            findNavController().navigate(R.id.action_triviaFragment_to_endQuizFragment)
        }
    }


    //button observers
    private fun quitButtonObserver() {
        triviaViewModel.onQuitButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                showQuitDialog()
                triviaViewModel.onQuitButtonClicked.value = false
            }
        }
    }

    private fun skipButtonObserver() {
        triviaViewModel.onSkipButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                navigateToEndQuiz()
                triviaViewModel.loadNextQuestion()
                triviaViewModel.onSkipButtonClicked.value = false
            }

        }
    }

    private fun nextButtonObserver() {
        triviaViewModel.onNextButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                navigateToEndQuiz()
                triviaViewModel.loadNextQuestion()
                triviaViewModel.onNextButtonClicked.value = false
            }

        }
    }

    private fun answer1ButtonObserver() {
        triviaViewModel.onAnswer1ButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                triviaViewModel.isRightAnswer(triviaViewModel.answer1.value.toString())
                triviaViewModel.onAnswer1ButtonClicked.value = false
            }
        }
    }

    private fun answer2ButtonObserver() {
        triviaViewModel.onAnswer2ButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                triviaViewModel.isRightAnswer(triviaViewModel.answer2.value.toString())
                triviaViewModel.onAnswer2ButtonClicked.value = false
            }
        }
    }

    private fun answer3ButtonObserver() {
        triviaViewModel.onAnswer3ButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                triviaViewModel.isRightAnswer(triviaViewModel.answer3.value.toString())
                triviaViewModel.onAnswer3ButtonClicked.value = false
            }
        }
    }

    private fun answer4ButtonObserver() {
        triviaViewModel.onAnswer4ButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                triviaViewModel.isRightAnswer(triviaViewModel.answer4.value.toString())
                triviaViewModel.onAnswer4ButtonClicked.value = false
            }
        }
    }

    private fun timerObserver() {
        triviaViewModel.triviaTimerLiveData.observe(viewLifecycleOwner) { remainingTime ->
            binding.quizTimer.text = "Time: $remainingTime s"
            if (triviaViewModel.timerFinished.value == true) {
                findNavController().navigate(R.id.action_triviaFragment_to_endQuizFragment)
                triviaViewModel.timerFinished.value = false
            }
        }
    }

    private fun timerFinishedObserver() {
        triviaViewModel.timerFinished.observe(viewLifecycleOwner) {
            if (triviaViewModel.timerFinished.value == true) {
                findNavController().navigate(R.id.action_triviaFragment_to_endQuizFragment)
                triviaViewModel.timerFinished.value = false
            }
        }
    }

    private fun insertDataToDatabase() {
        val chosenDifficulty = triviaViewModel.levelDifficulty.value
        val calculatedScore = triviaViewModel.score.value //TODO: modify with the calculated score
        val calculatedRank = 10

        val quizResult = QuizResult(0, chosenDifficulty, calculatedScore, calculatedRank)

        quizResultsViewModel.addQuizResult(quizResult)
        Toast.makeText(requireContext(), "Added Successfully", Toast.LENGTH_LONG).show()

    }
}