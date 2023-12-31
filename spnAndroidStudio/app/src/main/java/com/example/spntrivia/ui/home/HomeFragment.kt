package com.example.spntrivia.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.spntrivia.R
import com.example.spntrivia.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showQuitAppDialogue()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onResume() {
        super.onResume()
        showBottomNavigation()
    }

    private fun easyModeObserver() {
        homeViewModel.onEasyButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                hideBottomNavigation()
                navigateToTriviaFragment(1) // 1 for easy mode
                homeViewModel.onEasyButtonClicked.value = false
            }
        }
    }

    private fun mediumModeObserver() {
        homeViewModel.onMediumButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                hideBottomNavigation()
                navigateToTriviaFragment(2) // 2 for medium mode
                homeViewModel.onMediumButtonClicked.value = false
            }
        }
    }

    private fun hardModeObserver() {
        homeViewModel.onHardButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                hideBottomNavigation()
                navigateToTriviaFragment(3) // 3 for hard mode
                homeViewModel.onHardButtonClicked.value = false
            }
        }
    }

    private fun hideBottomNavigation() {
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView?.visibility = View.GONE
    }

    private fun showBottomNavigation() {
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView?.visibility = View.VISIBLE
    }

    private fun navigateToTriviaFragment(gameMode: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(getString(R.string.game_mode_key), gameMode)
        editor.apply()
        findNavController().navigate(R.id.action_homeFragment_to_triviaFragment)
    }

    private fun showQuitAppDialogue() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Leaving so Soon?")
            .setMessage("Are you sure you want to exit the app?")
            .setPositiveButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Yes") { _, _ ->
                activity?.finish()
            }
            .show()
    }
}