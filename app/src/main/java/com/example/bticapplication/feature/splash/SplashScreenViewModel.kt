package com.example.bticapplication.feature.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bticapplication.extensions.runBlocking
import com.example.bticapplication.feature.cinema.CinemaBrand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val repository: SplashScreenRepository
) : ViewModel() {
    private val brandCinemaRetrieveStatusMutable: MutableLiveData<BrandCinemaRetrieveStatus> =
        MutableLiveData()
    val brandCinemaRetrieveStatus: LiveData<BrandCinemaRetrieveStatus> =
        brandCinemaRetrieveStatusMutable

    fun getBrandCinemaList() = viewModelScope.launch {
        val result = runBlocking(
            onBlock = { repository.getBrandCinemaList() },
            onSuccess = { BrandCinemaRetrieveStatus.Success(it) },
            onError = { BrandCinemaRetrieveStatus.Error(it) }
        )
        brandCinemaRetrieveStatusMutable.value = result
    }
}

sealed class BrandCinemaRetrieveStatus {
    class Success(val list: List<CinemaBrand>) : BrandCinemaRetrieveStatus()
    class Error(val exception: Exception) : BrandCinemaRetrieveStatus()
    data object Loading : BrandCinemaRetrieveStatus()
}