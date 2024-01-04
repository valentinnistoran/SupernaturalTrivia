package com.example.spntrivia.ui.endQuiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.spntrivia.R
import com.example.spntrivia.databinding.FragmentEndQuizBinding
import com.example.spntrivia.gameHistoryDB.QuizResultsViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EndQuizFragment : Fragment() {

    private lateinit var binding: FragmentEndQuizBinding
    private val endQuizViewModel: EndQuizViewModel by viewModels()
    private lateinit var quizResultsViewModel: QuizResultsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEndQuizBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.endQuizViewModel = endQuizViewModel

        endQuizViewModel.loadRanks(requireContext())

        quizResultsViewModel = ViewModelProvider(this).get(QuizResultsViewModel::class.java)
        quizResultsViewModel.lastResult.observe(this, Observer { quizResult ->
            endQuizViewModel.observeLastQuizResult(quizResult)
        })

        backHomeObserver()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showBackHomeDialogue()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

    }
    private fun showBackHomeDialogue() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Back Home?")
            .setMessage("Are you sure you want to go to the home page?")
            .setPositiveButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Yes") { _, _ ->
                findNavController().popBackStack(R.id.navigation_home, false)
            }
            .show()
    }

    private fun backHomeObserver() {
        endQuizViewModel.onBackHomeButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                findNavController().popBackStack(R.id.navigation_home, false)
                endQuizViewModel.onBackHomeButtonClicked.value = false
            }

        }
    }
}