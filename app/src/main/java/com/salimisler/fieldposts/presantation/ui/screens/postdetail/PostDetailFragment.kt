package com.salimisler.fieldposts.presantation.ui.screens.postdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.salimisler.fieldposts.R
import com.salimisler.fieldposts.databinding.FragmentPostDetailBinding
import com.salimisler.fieldposts.presantation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostDetailFragment :
    BaseFragment<FragmentPostDetailBinding, PostDetailViewModel>(R.layout.fragment_post_detail) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPostDetailBinding
        get() = FragmentPostDetailBinding::inflate

    override val viewModel: PostDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFlows()
    }

    private fun initFlows() {
        lifecycleScope.launch {
            viewModel.postDetailSF.filterNotNull().collectLatest {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}