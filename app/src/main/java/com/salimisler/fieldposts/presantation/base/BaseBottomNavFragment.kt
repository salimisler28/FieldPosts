package com.salimisler.fieldposts.presantation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.salimisler.fieldposts.presantation.ui.MainActivity

abstract class BaseBottomNavFragment<VB : ViewBinding, VM : BaseViewModel> constructor(
    @LayoutRes layoutRes: Int
) : BaseFragment<VB, VM>(layoutRes) {
    private val bottomNavFragment by lazy {
        (requireActivity() as MainActivity)
            .mainNavHostFragment
            .childFragmentManager
            .fragments[0]
    }

    fun navigate(id: Int) {
        bottomNavFragment.findNavController()
            .navigate(id)
    }

    fun navigate(id: Int, data: Bundle) {
        bottomNavFragment.findNavController()
            .navigate(id, data)
    }

    fun navigate(navDirections: NavDirections) {
        bottomNavFragment.findNavController()
            .navigate(navDirections)
    }
}