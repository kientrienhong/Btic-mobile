package com.example.bticapplication.feature.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bticapplication.extensions.runBlocking
import com.example.bticapplication.feature.cinema.CinemaBrandRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeAdminViewModel @Inject constructor(
    private val cinemaBrandRepository: CinemaBrandRepository
) : ViewModel() {

    private val cinemaBrandItemViewListGetStatusMutable =
        MutableLiveData<CinemaBrandItemViewListGetStatus>()
    val cinemaBrandItemViewListGetStatus: LiveData<CinemaBrandItemViewListGetStatus> =
        cinemaBrandItemViewListGetStatusMutable

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
}

sealed class CinemaBrandItemViewListGetStatus {
    data object Loading : CinemaBrandItemViewListGetStatus()
    data class Success(val data: List<CinemaBrandItemView>) : CinemaBrandItemViewListGetStatus()
    data class Error(val exception: Exception) : CinemaBrandItemViewListGetStatus()
}
