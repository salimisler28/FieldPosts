package com.salimisler.fieldposts.presantation.ui.screens.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.salimisler.fieldposts.R
import com.salimisler.fieldposts.databinding.FragmentPostsBinding
import com.salimisler.fieldposts.presantation.base.BaseFragment
import com.salimisler.fieldposts.presantation.ui.screens.posts.adapter.PostsController
import com.salimisler.fieldposts.presantation.ui.utils.gone
import com.salimisler.fieldposts.presantation.ui.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PostFragment : BaseFragment<FragmentPostsBinding, PostsViewModel>(R.layout.fragment_posts) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPostsBinding
        get() = FragmentPostsBinding::inflate

    override val viewModel: PostsViewModel by viewModels()

    val postsController by lazy { PostsController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initFlows()
        initListeners()
    }

    private fun initRecyclerView() {
        binding.ervPosts.apply {
            adapter = postsController.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initFlows() {
        lifecycleScope.launchWhenCreated {
            viewModel.baseLoadingSF.collectLatest {
                handleLoading(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.postSF.collectLatest {
                postsController.posts = it
                postsController.requestModelBuild()
            }
        }
    }

    private fun initListeners() {
        postsController.onItemClickListener = {

        }

        postsController.onFavClickListener = {
            viewModel.toggleFav(it)
        }
    }

    private fun handleLoading(loading: Boolean) {
        if (loading) {
            binding.ervPosts.gone()
            binding.pbPosts.visible()
        } else {
            binding.ervPosts.visible()
            binding.pbPosts.gone()
        }
    }

}