package com.salimisler.fieldposts.presantation.ui.screens.bottomnav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.salimisler.fieldposts.R
import com.salimisler.fieldposts.databinding.FragmentBottomNavBinding
import com.salimisler.fieldposts.presantation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomNavFragment :
    BaseFragment<FragmentBottomNavBinding, BottomNavViewModel>(R.layout.fragment_bottom_nav) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBottomNavBinding
        get() = FragmentBottomNavBinding::inflate

    override val viewModel: BottomNavViewModel by viewModels()
}