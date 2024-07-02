package com.example.bticapplication.feature.admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bticapplication.R
import com.example.bticapplication.databinding.ActivityAdminBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    private lateinit var adapter: CinemaBrandAdapter
    private val viewModel by viewModels<CinemaBrandViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapter = CinemaBrandAdapter()
        binding.cinemaBrandListView.apply {
            adapter = this@HomeAdminActivity.adapter
            layoutManager = LinearLayoutManager(
                this@HomeAdminActivity,
                LinearLayoutManager.HORIZONTAL,
                false /* reverseLayout */
            )
        }
        observeData()
    }

    private fun observeData() {
        viewModel.cinemaBrandItemViewListGetStatus.observe(this) {
            when (it) {
                is CinemaBrandItemViewListGetStatus.Loading -> Unit
                is CinemaBrandItemViewListGetStatus.Success -> {
                    adapter.submit(it.data)
                }
                is CinemaBrandItemViewListGetStatus.Error -> {
                    Toast.makeText(this, it.exception.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, HomeAdminActivity::class.java)
    }
}