package com.example.bticapplication.feature.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bticapplication.R
import com.example.bticapplication.databinding.ActivitySplashScreenBinding
import com.example.bticapplication.extensions.parcelable
import com.example.bticapplication.feature.authen.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

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
    }

    private fun retrieveDataFromIntent(intent: Intent) {
        val user = intent.parcelable<User>(USER) ?: throw IllegalStateException("Not found user")

    }

    companion object {
        private const val USER = "user"
        fun createIntent(context: Context, user: User) =
            Intent(context, SplashScreenActivity::class.java).apply {
                putExtra(USER, user)
            }
    }
}