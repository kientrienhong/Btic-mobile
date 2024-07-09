package com.example.bticapplication.customview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bticapplication.databinding.MyConfirmDialogBinding
import com.example.bticapplication.extensions.runBlocking
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MyConfirmDialog(
    private val title: String,
    private val confirmButtonLabel: String,
    private val cancelButtonLabel: String,
    private val onConfirmClick: suspend () -> Unit,
) : DialogFragment() {
    private lateinit var binding: MyConfirmDialogBinding
    private val viewModel by viewModels<MyConfirmDialogViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyConfirmDialogBinding.inflate(layoutInflater)
        observeData()
        binding.btnConfirm.setOnClickListener {
            viewModel.onConfirmClick(onConfirmClick)
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.content.text = title
        binding.btnConfirm.text = confirmButtonLabel
        binding.btnCancel.text = cancelButtonLabel

        return binding.root
    }

    private fun observeData() {
        viewModel.clickActionStatusLiveData.observe(viewLifecycleOwner) {
            binding.btnConfirm.setIsLoading(it is MyConfirmDialogViewModel.ClickActionStatus.Loading)
            when (it) {
                is MyConfirmDialogViewModel.ClickActionStatus.Loading -> Unit

                is MyConfirmDialogViewModel.ClickActionStatus.Success -> {
                    dismiss()
                }

                is MyConfirmDialogViewModel.ClickActionStatus.Failure -> {
                    Toast.makeText(requireContext(), it.exception.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}

@HiltViewModel
private class MyConfirmDialogViewModel @Inject constructor() : ViewModel() {

    private val clickActionStatusMutableLiveData = MutableLiveData<ClickActionStatus>()
    val clickActionStatusLiveData: LiveData<ClickActionStatus> = clickActionStatusMutableLiveData

    fun onConfirmClick(onConfirmClick: suspend () -> Unit) = viewModelScope.launch {
        val result = runBlocking(
            onBlock = onConfirmClick,
            onSuccess = { ClickActionStatus.Success },
            onError = { ClickActionStatus.Failure(exception = it) }
        )
        clickActionStatusMutableLiveData.value = result
    }


    sealed class ClickActionStatus {
        data object Loading : ClickActionStatus()
        data object Success : ClickActionStatus()
        class Failure(val exception: Exception) : ClickActionStatus()
    }
}