package com.example.bticapplication.feature.admin.cinema

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.bticapplication.extensions.runBlocking
import com.example.bticapplication.feature.cinema.CinemaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CinemaViewModel @Inject constructor(
    private val cinemaRepository: CinemaRepository
) : ViewModel() {

    fun getListCinema(cinemaBrandId: Int) = liveData {
        val result = runBlocking(
            onBlock = { cinemaRepository.getList(cinemaBrandId) },
            onSuccess = { CinemaApiResult.Success(it) },
            onError = { CinemaApiResult.Error(it) }
        )
        emit(result)
    }


}