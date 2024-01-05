package com.example.spntrivia.ui.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var quizResultsViewModel: QuizResultsViewModel

    private lateinit var sharedPreferences: SharedPreferences
    private var currentUsername: String? = null
    private var currentFavoriteCharacter: String? = null
    private lateinit var currentProfileImagePath: String

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
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        quizResultsViewModel = ViewModelProvider(this).get(QuizResultsViewModel::class.java)
        quizResultsViewModel.readAllData.observe(this, Observer { quizResult ->
            adapter.setData(quizResult)
        })

        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        currentUsername = sharedPreferences.getString("currentUsername", "username")
        currentFavoriteCharacter = sharedPreferences.getString("currentFavoriteCharacter", "favourite character")
        currentProfileImagePath = sharedPreferences.getString("profileImagePath", "")!!

        binding.usernameField.setText(currentUsername)
        binding.favouriteCharacter.setText(currentFavoriteCharacter)
        loadImageFromInternalStorage()

        binding.usernameField.addTextChangedListener(getTextWatcher())
        binding.favouriteCharacter.addTextChangedListener(getTextWatcher())

        deleteButtonObserver()

        return binding.root
    }

    private fun getTextWatcher() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            saveUserInfo()
        }
    }

    private fun saveUserInfo() {
        val newUsername = binding.usernameField.text.toString()
        val newFavoriteCharacter = binding.favouriteCharacter.text.toString()

        sharedPreferences.edit().putString("currentUsername", newUsername).apply()
        sharedPreferences.edit().putString("currentFavoriteCharacter", newFavoriteCharacter).apply()
        currentUsername = newUsername
        currentFavoriteCharacter = newFavoriteCharacter
    }

    private fun deleteButtonObserver() {
        profileViewModel.onDeleteButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                showDeleteDialogue()
                profileViewModel.onDeleteButtonClicked.value = false
            }
        }
    }

    private fun showDeleteDialogue() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete History?")
            .setMessage("Are you sure you want to delete your Quiz History?")
            .setPositiveButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Yes") { _, _ ->
                quizResultsViewModel.readAllData.observe(this, Observer { quizResult ->
                    quizResultsViewModel.deleteQuizResult(quizResult)
                })
            }
            .show()
    }

    private fun loadImageFromInternalStorage() {
        if (currentProfileImagePath.isNotEmpty()) {
            try {
                val file = File(currentProfileImagePath)
                val bitmap = BitmapFactory.decodeStream(FileInputStream(file))
                binding.profileImage.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun openImagePicker() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            binding.profileImage.setImageURI(selectedImageUri)
            if (selectedImageUri != null) {
                saveImageToInternalStorage(selectedImageUri)
            }
        }
    }

    private fun saveImageToInternalStorage(selectedImageUri: Uri) {
        try {
            val inputStream = requireContext().contentResolver.openInputStream(selectedImageUri)
            val file = File(requireContext().filesDir, "profile_image.jpg")
            val outputStream = FileOutputStream(file)

            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }

            currentProfileImagePath = file.absolutePath
            sharedPreferences.edit().putString("profileImagePath", currentProfileImagePath).apply()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}
