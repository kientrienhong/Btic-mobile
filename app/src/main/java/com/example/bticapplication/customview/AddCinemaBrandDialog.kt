package com.example.bticapplication.customview

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.bticapplication.databinding.AddCinemaBrandDialogLayoutBinding
import com.example.bticapplication.feature.admin.cinemabrand.CinemaBrandCreateStatus
import com.example.bticapplication.feature.admin.cinemabrand.CinemaBrandViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCinemaBrandDialog : DialogFragment() {
    private lateinit var binding: AddCinemaBrandDialogLayoutBinding
    private val viewModel: CinemaBrandViewModel by activityViewModels()
    private var selectedImageUri: Uri? = null

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            selectedImageUri = uri
            if (uri != null) {
                handleSelectMedia(uri)
            } else {
                val message = "No media selected"
                Log.d("PhotoPicker", message)
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddCinemaBrandDialogLayoutBinding.inflate(layoutInflater)
        binding.btnCreate.setOnClickListener {
            val imageUri = selectedImageUri ?: return@setOnClickListener
            viewModel.createCinemaBrand(imageUri, binding.edtName.text)
        }
        binding.addImageLayout.setOnClickListener {
            pickMedia.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }
        observeData()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }


    private fun observeData() {
        viewModel.cinemaBrandCreateStatus.observe(viewLifecycleOwner) {
            binding.btnCreate.setIsLoading(it is CinemaBrandCreateStatus.Loading)
            when (it) {
                is CinemaBrandCreateStatus.Success -> {
                    dismiss()
                }
                is CinemaBrandCreateStatus.Error -> {
                    Log.d(TAG, it.exception.toString())
                    Toast.makeText(requireContext(), it.exception.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is CinemaBrandCreateStatus.Loading -> Unit
            }
        }
    }

    private fun handleSelectMedia(uri: Uri) {
        val cr = requireContext().contentResolver
        val inputStream = cr.openInputStream(uri)
        val drawable = Drawable.createFromStream(inputStream, uri.toString())
        binding.cinemaLogo.setImageDrawable(drawable)
        binding.cinemaLogo.isVisible = true
        binding.addImageLayout.isVisible = false
    }

    companion object {
        private const val TAG = "AddCinemaBrandDialogView"
    }
}


