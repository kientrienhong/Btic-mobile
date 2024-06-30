package com.example.bticapplication.feature.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bticapplication.R
import com.example.bticapplication.databinding.ActivitySplashScreenBinding
import com.example.bticapplication.extensions.parcelable
import com.example.bticapplication.feature.admin.HomeAdminActivity
import com.example.bticapplication.feature.authen.model.Role
import com.example.bticapplication.feature.authen.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val viewModel by viewModels<SplashScreenViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val user = retrieveUserFromIntent(intent)
        observeData()
        if (user.role == Role.Admin) {
            viewModel.getBrandCinemaList()
        }
    }

    private fun retrieveUserFromIntent(intent: Intent): User =
        intent.parcelable<User>(USER) ?: throw IllegalStateException("Not found user")

    private fun observeData() {
        viewModel.brandCinemaRetrieveStatus.observe(this) {
            when (it) {
                is BrandCinemaRetrieveStatus.Error -> {
                    Log.e(TAG, it.exception.message, it.exception)
                    Toast.makeText(this, it.exception.message, Toast.LENGTH_SHORT).show()
                }

                is BrandCinemaRetrieveStatus.Success -> {
                    startActivity(HomeAdminActivity.createIntent(this))
                }

                is BrandCinemaRetrieveStatus.Loading -> Unit
            }
        }
    }

    companion object {
        private val TAG = SplashScreenActivity::class.java.simpleName
        private const val USER = "user"
        fun createIntent(context: Context, user: User) =
            Intent(context, SplashScreenActivity::class.java).apply {
                putExtra(USER, user)
            }
    }
}