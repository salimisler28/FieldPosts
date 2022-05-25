package com.salimisler.fieldposts.presantation.ui.screens.postdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.salimisler.fieldposts.R
import com.salimisler.fieldposts.databinding.FragmentPostDetailBinding
import com.salimisler.fieldposts.presantation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment :
    BaseFragment<FragmentPostDetailBinding, PostDetailViewModel>(R.layout.fragment_post_detail) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPostDetailBinding
        get() = FragmentPostDetailBinding::inflate

    override val viewModel: PostDetailViewModel by viewModels()
}