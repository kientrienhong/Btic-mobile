package com.example.bticapplication.feature.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bticapplication.extensions.runBlocking
import com.example.bticapplication.feature.cinema.CinemaBrand
import com.example.bticapplication.feature.cinema.CinemaBrandRepository
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject

@HiltViewModel
class CinemaBrandViewModel @Inject constructor(
    private val cinemaBrandRepository: CinemaBrandRepository
) : ViewModel() {

    private val cinemaBrandItemViewListGetStatusMutable =
        MutableLiveData<CinemaBrandItemViewListGetStatus>()
    val cinemaBrandItemViewListGetStatus: LiveData<CinemaBrandItemViewListGetStatus> =
        cinemaBrandItemViewListGetStatusMutable

    private val cinemaBrandCreateStatusMutable = MutableLiveData<CinemaBrandCreateStatus>()
    val cinemaBrandCreateStatus: LiveData<CinemaBrandCreateStatus> = cinemaBrandCreateStatusMutable

    init {
        getCinemaList()
    }

    private fun getCinemaList() = viewModelScope.launch {
        cinemaBrandItemViewListGetStatusMutable.value = CinemaBrandItemViewListGetStatus.Loading
        val result = runBlocking(
            onBlock = { cinemaBrandRepository.getList() },
            onSuccess = {
                val cinemaBrandItemViewList = it.mapIndexed { index, cinemaBrand ->
                    CinemaBrandItemView(cinemaBrand, isSelected = index == 0)
                }
                CinemaBrandItemViewListGetStatus.Success(cinemaBrandItemViewList)
            },
            onError = { CinemaBrandItemViewListGetStatus.Error(it) }
        )
        cinemaBrandItemViewListGetStatusMutable.value = result
    }

    fun createCinemaBrand(image: File, name: String) = viewModelScope.launch {
        cinemaBrandCreateStatusMutable.value = CinemaBrandCreateStatus.Loading
        val stream = FileInputStream(image)
        val storageRef = Firebase.storage.reference
        val mountainsRef = storageRef.child("images/$name")
        val uploadTask = mountainsRef.putStream(stream)

        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            mountainsRef.downloadUrl
        }.addOnFailureListener {
            cinemaBrandCreateStatusMutable.value = CinemaBrandCreateStatus.Error(it)
            return@addOnFailureListener
        }.addOnCompleteListener { task ->
            viewModelScope.launch {
                val downloadUriString = task.result.toString()
                val result = runBlocking(
                    onBlock = {
                        cinemaBrandRepository.create(
                            CinemaBrand.newInstanceWithoutId(
                                name,
                                downloadUriString
                            )
                        )
                    },
                    onSuccess = { CinemaBrandCreateStatus.Success },
                    onError = { CinemaBrandCreateStatus.Error(it) }
                )
                cinemaBrandCreateStatusMutable.value = result
            }
        }
    }
}

sealed class CinemaBrandItemViewListGetStatus {
    data object Loading : CinemaBrandItemViewListGetStatus()
    data class Success(val data: List<CinemaBrandItemView>) : CinemaBrandItemViewListGetStatus()
    data class Error(val exception: Exception) : CinemaBrandItemViewListGetStatus()
}

sealed class CinemaBrandCreateStatus {
    data object Loading : CinemaBrandCreateStatus()
    data object Success : CinemaBrandCreateStatus()
    data class Error(val exception: Exception) : CinemaBrandCreateStatus()
}
