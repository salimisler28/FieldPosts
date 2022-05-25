package com.salimisler.fieldposts.presantation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.salimisler.fieldposts.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var mainNavHostFragment: NavHostFragment
    lateinit var mainNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_main_activity) as NavHostFragment
        mainNavController = mainNavHostFragment.navController
    }
}