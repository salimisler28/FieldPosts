package com.salimisler.fieldposts.presantation.ui.screens.bottomnav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.salimisler.fieldposts.R
import com.salimisler.fieldposts.databinding.FragmentBottomNavBinding
import com.salimisler.fieldposts.presantation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomNavFragment :
    BaseFragment<FragmentBottomNavBinding, BottomNavViewModel>(R.layout.fragment_bottom_nav) {
    lateinit var bottomNavHostFragment: NavHostFragment
    lateinit var bottomNavController: NavController

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBottomNavBinding
        get() = FragmentBottomNavBinding::inflate

    override val viewModel: BottomNavViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavHostFragment =
            childFragmentManager.findFragmentById(R.id.fcv_bottom_nav) as NavHostFragment
        bottomNavController = bottomNavHostFragment.navController

        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setupWithNavController(bottomNavController)
    }
}