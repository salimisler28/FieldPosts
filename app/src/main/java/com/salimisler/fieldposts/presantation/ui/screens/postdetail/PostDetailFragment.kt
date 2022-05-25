package com.salimisler.fieldposts.presantation.ui.screens.postdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.salimisler.fieldposts.R
import com.salimisler.fieldposts.databinding.FragmentPostDetailBinding
import com.salimisler.fieldposts.presantation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment :
    BaseFragment<FragmentPostDetailBinding, PostDetailViewModel>(R.layout.fragment_post_detail) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPostDetailBinding
        get() = FragmentPostDetailBinding::inflate

    private val args: PostDetailFragmentArgs by navArgs()
    override val viewModel: PostDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("fafa", viewModel.postId.toString())
    }
}