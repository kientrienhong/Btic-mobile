package com.example.bticapplication.feature.authen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bticapplication.extensions.runBlocking
import com.example.bticapplication.feature.authen.model.SignInRequest
import com.example.bticapplication.feature.authen.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val authStateMutableLiveData = MutableLiveData<AuthState>(AuthState.Initial)
    val authState: LiveData<AuthState> = authStateMutableLiveData

    fun signIn(email: String, password: String) = viewModelScope.launch {
        val result = runBlocking(
            onBlock = { authRepository.signIn(SignInRequest(email, password)) },
            onSuccess = {
                val user = it.user.copy()
                AuthState.Success(user)
            },
            onError = {
                Log.e(TAG, "Exception: $it")
                AuthState.Error(it)
            }
        )

        authStateMutableLiveData.postValue(result)
    }


    sealed class AuthState {
        data object Initial : AuthState()
        data object Loading : AuthState()
        class Success(val user: User) : AuthState()
        data class Error(val exception: Exception) : AuthState()
    }

    companion object {
        const val TAG = "AuthViewModel"
    }
}