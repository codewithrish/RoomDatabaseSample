package com.codewithrish.roomdatabasesample.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.codewithrish.roomdatabasesample.R
import com.codewithrish.roomdatabasesample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)

        // Find nav host fragment
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragment_container_view
        ) as NavHostFragment

        // Setup Nav Controller
        navController = navHostFragment.navController

    }
}