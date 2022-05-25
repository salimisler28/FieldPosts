package com.salimisler.fieldposts.presantation.ui.screens.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.salimisler.fieldposts.R
import com.salimisler.fieldposts.databinding.FragmentPostsBinding
import com.salimisler.fieldposts.presantation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : BaseFragment<FragmentPostsBinding, PostsViewModel>(R.layout.fragment_posts) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPostsBinding
        get() = FragmentPostsBinding::inflate

    override val viewModel: PostsViewModel by viewModels()
}