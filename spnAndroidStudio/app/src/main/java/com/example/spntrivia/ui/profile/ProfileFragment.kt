package com.example.spntrivia.ui.profile

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spntrivia.databinding.FragmentProfileBinding
import com.example.spntrivia.gameHistoryDB.QuizResultsViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var quizResultsViewModel: QuizResultsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.profileViewModel = profileViewModel

        binding.profileImage.setOnClickListener {
            openImagePicker()
        }

        val adapter = HistoryAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager =LinearLayoutManager(requireContext())


        quizResultsViewModel = ViewModelProvider(this).get(QuizResultsViewModel::class.java)
        quizResultsViewModel.readAllData.observe(this, Observer { quizResult ->
            adapter.setData(quizResult)
        })

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            binding.profileImage.setImageURI(selectedImageUri)
        }
    }

    private fun openImagePicker() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
    }


    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

}