package com.example.spntrivia.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.spntrivia.R
import com.example.spntrivia.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeViewModel = homeViewModel


        sharedPreferences = activity!!.getSharedPreferences(
            getString(R.string.shared_preference_game_mode), Context.MODE_PRIVATE
        )

        easyModeObserver()
        mediumModeObserver()
        hardModeObserver()

        return binding.root

    }

    private fun easyModeObserver() {
        homeViewModel.onEasyButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                val editor = sharedPreferences.edit()
                editor.putInt(getString(R.string.game_mode_key), 1)//1 for easy mode
                editor.apply()
                findNavController().navigate(R.id.action_homeFragment_to_triviaFragment)
                Toast.makeText(requireContext(), "From Easy", Toast.LENGTH_LONG).show()
                homeViewModel.onEasyButtonClicked.value = false
            }

        }
    }

    private fun mediumModeObserver() {
        homeViewModel.onMediumButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                val editor = sharedPreferences.edit()
                editor.putInt(getString(R.string.game_mode_key), 2)//2 for medium mode
                editor.apply()
                findNavController().navigate(R.id.action_homeFragment_to_triviaFragment)
                Toast.makeText(requireContext(), "From Medium", Toast.LENGTH_LONG).show()
                homeViewModel.onMediumButtonClicked.value = false
            }

        }
    }

    private fun hardModeObserver() {
        homeViewModel.onHardButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                val editor = sharedPreferences.edit()
                editor.putInt(getString(R.string.game_mode_key), 3)//3 for hard mode
                editor.apply()
                findNavController().navigate(R.id.action_homeFragment_to_triviaFragment)
                Toast.makeText(requireContext(), "From Hard", Toast.LENGTH_LONG).show()
                homeViewModel.onHardButtonClicked.value = false
            }

        }
    }
}