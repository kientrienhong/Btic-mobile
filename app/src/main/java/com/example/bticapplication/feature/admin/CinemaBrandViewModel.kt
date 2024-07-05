package com.example.bticapplication.feature.admin

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.bticapplication.extensions.runBlocking
import com.example.bticapplication.feature.cinema.CinemaBrand
import com.example.bticapplication.feature.cinema.CinemaBrandRepository
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CinemaBrandViewModel @Inject constructor(
    private val cinemaBrandRepository: CinemaBrandRepository
) : ViewModel() {

    private val cinemaBrandFetchingStatusMutable =
        MutableLiveData<CinemaBrandFetchingStatus>()
    val cinemaBrandFetchingStatus: LiveData<CinemaBrandFetchingStatus> =
        cinemaBrandFetchingStatusMutable

    private val cinemaBrandCreateStatusMutable = MutableLiveData<CinemaBrandCreateStatus>()
    val cinemaBrandCreateStatus: LiveData<CinemaBrandCreateStatus> = cinemaBrandCreateStatusMutable

    init {
        fetchList()
    }

    fun getCinemaList(): LiveData<List<CinemaBrandItemView>> = cinemaBrandRepository.getList().map {
        it.map { cinemaBrand -> CinemaBrandItemView(cinemaBrand, isSelected = false) }
    }

    fun createCinemaBrand(uri: Uri, name: String) = viewModelScope.launch {
        cinemaBrandCreateStatusMutable.value = CinemaBrandCreateStatus.Loading
        val storageRef = Firebase.storage.reference
        val mountainsRef = storageRef.child("images/$name")
        val uploadTask = mountainsRef.putFile(uri)

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

    private fun fetchList() = viewModelScope.launch {
        cinemaBrandFetchingStatusMutable.value = CinemaBrandFetchingStatus.Loading
        val result = runBlocking(
            onBlock = { cinemaBrandRepository.fetchList() },
            onSuccess = { CinemaBrandFetchingStatus.Success },
            onError = { CinemaBrandFetchingStatus.Error(it) }
        )
        cinemaBrandFetchingStatusMutable.value = result
    }
}

sealed class CinemaBrandFetchingStatus {
    data object Loading : CinemaBrandFetchingStatus()
    data object Success : CinemaBrandFetchingStatus()
    data class Error(val exception: Exception) : CinemaBrandFetchingStatus()
}

sealed class CinemaBrandCreateStatus {
    data object Loading : CinemaBrandCreateStatus()
    data object Success : CinemaBrandCreateStatus()
    data class Error(val exception: Exception) : CinemaBrandCreateStatus()
}
