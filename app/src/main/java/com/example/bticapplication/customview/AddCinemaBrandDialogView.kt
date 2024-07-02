package com.example.bticapplication.customview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.bticapplication.databinding.AddCinemaBrandDialogLayoutBinding
import com.example.bticapplication.feature.admin.CinemaBrandCreateStatus
import com.example.bticapplication.feature.admin.CinemaBrandViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCinemaBrandDialogView : DialogFragment() {
    private lateinit var binding: AddCinemaBrandDialogLayoutBinding
    private val viewModel: CinemaBrandViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddCinemaBrandDialogLayoutBinding.inflate(layoutInflater)
        binding.btnCreate.setOnClickListener {
        }
        observeData()
        return binding.root
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

    companion object {
        private const val TAG = "AddCinemaBrandDialogView"
    }
}


