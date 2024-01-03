package com.example.spntrivia.ui.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.spntrivia.R
import com.example.spntrivia.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.settingsViewModel = settingsViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        helpButtonObserver()
        aboutUsButtonObserver()
        shareButtonObserver()
    }

    private fun helpButtonObserver() {
        settingsViewModel.onHelpButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                val helpView: TextView = binding.root.findViewById(R.id.helpView)
                val helpViewCloseButton: Button =
                    binding.root.findViewById(R.id.helpViewCloseButton)
                val helpButton: Button = binding.root.findViewById(R.id.shareButton)
                val aboutUsButton: Button = binding.root.findViewById(R.id.aboutUsButton)
                val shareButton: Button = binding.root.findViewById(R.id.helpButton)

                helpButton.visibility = View.GONE
                aboutUsButton.visibility = View.GONE
                shareButton.visibility = View.GONE

                helpView.visibility = View.VISIBLE
                helpViewCloseButton.visibility = View.VISIBLE

                helpViewCloseButton.setOnClickListener {
                    helpView.visibility = View.GONE
                    helpViewCloseButton.visibility = View.GONE
                    helpButton.visibility = View.VISIBLE
                    aboutUsButton.visibility = View.VISIBLE
                    shareButton.visibility = View.VISIBLE
                }
            }

        }
    }

    private fun aboutUsButtonObserver() {
        settingsViewModel.onAboutUsButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                val uri: Uri =
                    Uri.parse("https://docs.google.com/document/d/1Dhmuhbp97Jo7A0QBzf4LvDxrnO-l2MrWDXZPyNqtNIg/edit?usp=sharing")
                val intent = Intent(Intent.ACTION_VIEW, uri)

                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        requireContext(),
                        "The About Us file can't be opened.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
    }

    private fun shareButtonObserver() {
        settingsViewModel.onShareButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Check out my new app, SPN TRIVIA! https://docs.google.com/document/d/1Dhmuhbp97Jo7A0QBzf4LvDxrnO-l2MrWDXZPyNqtNIg/edit?usp=sharing"
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)

                try {
                    startActivity(shareIntent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        requireContext(),
                        "No app available for sharing",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
    }

}