package com.example.bticapplication.feature.authen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bticapplication.R
import com.example.bticapplication.databinding.ActivityAuthenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenBinding
    private val viewModel by viewModels<AuthenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAuthenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        AuthenViewController(this, binding, viewModel)
    }
}