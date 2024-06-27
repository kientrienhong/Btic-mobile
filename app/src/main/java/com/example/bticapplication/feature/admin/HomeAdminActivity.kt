package com.example.bticapplication.feature.admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bticapplication.R
import com.example.bticapplication.feature.cinema.CinemaBrand
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {

        private const val LIST_BRAND_NAME = "list_brand_name"
        fun createIntent(context: Context, listBrandName: ArrayList<CinemaBrand>): Intent = Intent(context, HomeAdminActivity::class.java).apply {
            putParcelableArrayListExtra(LIST_BRAND_NAME, listBrandName)
        }
    }
}