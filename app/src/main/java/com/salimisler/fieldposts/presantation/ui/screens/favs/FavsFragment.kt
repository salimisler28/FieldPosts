package com.salimisler.fieldposts.presantation.ui.screens.favs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.salimisler.fieldposts.R
import com.salimisler.fieldposts.databinding.FragmentFavsBinding
import com.salimisler.fieldposts.presantation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavsFragment : BaseFragment<FragmentFavsBinding, FavsViewModel>(R.layout.fragment_favs) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavsBinding
        get() = FragmentFavsBinding::inflate

    override val viewModel: FavsViewModel by viewModels()
}