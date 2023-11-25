package com.example.spntrivia.ui.endQuiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.spntrivia.databinding.FragmentEndQuizBinding

class EndQuizFragment : Fragment() {

    private lateinit var binding: FragmentEndQuizBinding
    private val endQuizViewModel: EndQuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEndQuizBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.endQuizViewModel = endQuizViewModel
        return binding.root

    }
}