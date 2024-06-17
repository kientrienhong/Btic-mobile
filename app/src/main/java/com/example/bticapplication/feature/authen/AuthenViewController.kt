package com.example.bticapplication.feature.authen

import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.bticapplication.databinding.ActivityAuthenBinding
import com.example.bticapplication.feature.admin.HomeAdminActivity
import com.example.bticapplication.feature.authen.model.Role
import com.example.bticapplication.feature.user.home.HomeUserActivity

class AuthenViewController(
    private val activity: ComponentActivity,
    private val binding: ActivityAuthenBinding,
    private val viewModel: AuthenViewModel
) {

    init {
        initView()
        observeData()
    }

    private fun initView() {
        binding.btnSignIn.setOnClickListener { signInOnClick() }
    }

    private fun signInOnClick() {
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
                    if (it.user.role == Role.Admin) {
                        activity.startActivity(HomeAdminActivity.createIntent(activity))
                    } else {
                        activity.startActivity(HomeUserActivity.createIntent(activity))
                    }
                }

                is AuthenViewModel.AuthState.Error -> {
                    binding.btnSignIn.setOnClickListener { signInOnClick() }
                    Toast.makeText(activity, it.exception.message, Toast.LENGTH_SHORT).show()
                }

                is AuthenViewModel.AuthState.Initial -> {
                    binding.btnSignIn.setIsLoading(false)
                }
            }
        }
    }
}