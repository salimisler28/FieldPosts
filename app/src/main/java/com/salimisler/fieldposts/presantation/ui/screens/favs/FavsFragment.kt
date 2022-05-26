package com.salimisler.fieldposts.presantation.ui.screens.favs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.salimisler.fieldposts.NavMainActivityDirections
import com.salimisler.fieldposts.R
import com.salimisler.fieldposts.databinding.FragmentFavsBinding
import com.salimisler.fieldposts.presantation.base.BaseBottomNavFragment
import com.salimisler.fieldposts.presantation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FavsFragment :
    BaseBottomNavFragment<FragmentFavsBinding, FavsViewModel>(R.layout.fragment_favs) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavsBinding
        get() = FragmentFavsBinding::inflate

    override val viewModel: FavsViewModel by viewModels()

    private val favsAdapter by lazy { FavsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initFlows()
        initListeners()
    }

    private fun initListeners() {
        favsAdapter.onItemClickListener = {
            navigateToDetailScreen(it.id)
        }
    }

    private fun initFlows() {
        lifecycleScope.launchWhenCreated {
            viewModel.favsSF.collectLatest {
                it?.let { favsAdapter.submitList(it) }
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvFavs.apply {
            adapter = favsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun navigateToDetailScreen(id: Int) {
        val action = NavMainActivityDirections.actionGlobalToPostDetail(id)
        navigate(action)
    }
}