package com.example.bticapplication.feature.admin

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bticapplication.R
import com.example.bticapplication.customview.AddCinemaBrandDialog
import com.example.bticapplication.databinding.ActivityAdminBinding
import com.example.bticapplication.feature.admin.cinema.CinemaAdapter
import com.example.bticapplication.feature.admin.cinema.CinemaApiResult
import com.example.bticapplication.feature.admin.cinema.CinemaViewModel
import com.example.bticapplication.feature.admin.cinemabrand.CinemaBrandAdapter
import com.example.bticapplication.feature.admin.cinemabrand.CinemaBrandFetchingStatus
import com.example.bticapplication.feature.admin.cinemabrand.CinemaBrandViewModel
import com.example.bticapplication.feature.cinema.Cinema
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    private lateinit var cinemaBrandAdapter: CinemaBrandAdapter
    private lateinit var cinemaAdapter: CinemaAdapter

    private val cinemaBrandViewModel by viewModels<CinemaBrandViewModel>()
    private val cinemaViewModel by viewModels<CinemaViewModel>()

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
        cinemaBrandAdapter = CinemaBrandAdapter(
            supportFragmentManager,
            ::showAddCinemaBrandDialog,
            cinemaBrandViewModel::deleteCinemaBrand,
        ) {
            cinemaViewModel.getListCinema(it).observe(this, ::handleGetListCinemaResult)
        }
        cinemaAdapter = CinemaAdapter()
        binding.cinemaBrandListView.apply {
            adapter = this@HomeAdminActivity.cinemaBrandAdapter
            layoutManager = LinearLayoutManager(
                this@HomeAdminActivity,
                LinearLayoutManager.HORIZONTAL,
                false /* reverseLayout */
            )
        }
        binding.cinemaListView.apply {
            adapter = this@HomeAdminActivity.cinemaAdapter
            layoutManager = LinearLayoutManager(this@HomeAdminActivity)
        }
        observeData()
    }

    private fun observeData() {
        cinemaBrandViewModel.getCinemaList().observe(this) {
            cinemaBrandAdapter.submit(it)
            cinemaBrandAdapter.listenChangeOnce {
                cinemaBrandAdapter.setSelectedId(cinemaBrandAdapter.selectedId)
            }
        }

        cinemaBrandViewModel.cinemaBrandFetchingStatus.observe(this) {
            when (it) {
                is CinemaBrandFetchingStatus.Loading,
                is CinemaBrandFetchingStatus.Success -> Unit

                is CinemaBrandFetchingStatus.Error -> {
                    Toast.makeText(this, it.exception.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showAddCinemaBrandDialog() {
        val dialog = AddCinemaBrandDialog()
        dialog.show(supportFragmentManager, TAG)
    }

    private fun handleGetListCinemaResult(getListCinemaResult: CinemaApiResult<List<Cinema>>) {
        Log.d("Test===", "result $getListCinemaResult")
        when (getListCinemaResult) {
            is CinemaApiResult.Success -> {
                cinemaAdapter.submitList(getListCinemaResult.data)
            }

            is CinemaApiResult.Error -> {
                Toast.makeText(this, getListCinemaResult.error.message, Toast.LENGTH_SHORT).show()
            }

            is CinemaApiResult.Loading -> {

            }
        }
    }

    companion object {
        private const val TAG = "AddCinemaBrandDialog"
        fun createIntent(context: Context): Intent = Intent(context, HomeAdminActivity::class.java)
    }
}