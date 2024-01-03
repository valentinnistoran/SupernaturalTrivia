package com.example.spntrivia.ui.info

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spntrivia.R
import com.example.spntrivia.databinding.FragmentInfoBinding
import java.util.*

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private val infoViewModel: InfoViewModel by viewModels()

    private var currentFunFactId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentInfoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.infoViewModel = infoViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        infoViewModel.loadFunFacts(requireContext())
        displayFunFact(0)

        refreshFunFactButtonObserver()
        instagramButtonObserver()
        twitterButtonObserver()
    }

    private fun refreshFunFactButtonObserver() {
        infoViewModel.onRefreshFunFactButton.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                var newId: Int
                do {
                    newId = Random().nextInt(38)
                } while (newId == currentFunFactId)

                currentFunFactId = newId

                displayFunFact(newId)

                infoViewModel.onRefreshFunFactButton.value = false
            }
        }
    }

    private fun displayFunFact(id: Int) {
        val funFactTextView: TextView = binding.root.findViewById(R.id.funFactTextView)

        infoViewModel.funFactsList?.let { funFactsList ->
            val funFact = funFactsList.find { it.id == id }

            funFact?.let {
                val funFactText = "${it.title}\n\n${it.text}"
                funFactTextView.text = funFactText
            }
        }
    }

    private fun instagramButtonObserver() {
        infoViewModel.onInstagramButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                val uri = Uri.parse("http://instagram.com/_u/cw_supernatural")
                val intent = Intent(Intent.ACTION_VIEW, uri)

                intent.setPackage("com.instagram.android")

                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    openUrl("http://instagram.com/cw_supernatural")
                }
                infoViewModel.onInstagramButtonClicked.value = false
            }

        }
    }

    private fun twitterButtonObserver() {
        infoViewModel.onTwitterButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                val uri = Uri.parse("twitter://user?screen_name=cw_spn")
                val intent = Intent(Intent.ACTION_VIEW, uri)

                intent.setPackage("com.twitter.android")

                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    openUrl("https://twitter.com/cw_spn")
                }
                infoViewModel.onTwitterButtonClicked.value = false
            }

        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}