package com.example.bticapplication.feature.authen

import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.bticapplication.databinding.ActivityAuthenBinding
import com.example.bticapplication.feature.splash.SplashScreenActivity

class AuthenViewController(
    private val activity: ComponentActivity,
    private val binding: ActivityAuthenBinding,
    private val viewModel: AuthenViewModel
) {

    init {
        initView()
        observeData()
        signIn()
    }

    private fun initView() {
        binding.btnSignIn.setOnClickListener { signIn() }
    }

    private fun signIn() {
        val email: String = binding.edtEmail.text
        val password: String = binding.edtPassword.text
        viewModel.signIn(email, password)
    }

    private fun observeData() {
        viewModel.authState.observe(activity) {
            val isLoadingState = it is AuthenViewModel.AuthState.Loading
            binding.btnSignIn.setIsLoading(isLoadingState)

            when (it) {
                is AuthenViewModel.AuthState.Loading -> {
                    binding.btnSignIn.setOnClickListener { }
                }

                is AuthenViewModel.AuthState.Success -> {
                    activity.startActivity(SplashScreenActivity.createIntent(activity, it.user))
                }

                is AuthenViewModel.AuthState.Error -> {
                    binding.btnSignIn.setOnClickListener { signIn() }
                    Toast.makeText(activity, it.exception.message, Toast.LENGTH_SHORT).show()
                }

                is AuthenViewModel.AuthState.Initial -> {
                    binding.btnSignIn.setIsLoading(false)
                }
            }
        }
    }
}