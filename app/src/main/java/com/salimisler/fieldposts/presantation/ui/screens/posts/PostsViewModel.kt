package com.salimisler.fieldposts.presantation.ui.screens.posts

import androidx.lifecycle.viewModelScope
import com.salimisler.fieldposts.core.Resource
import com.salimisler.fieldposts.domain.model.PostUiModel
import com.salimisler.fieldposts.domain.usecases.InitPostScreenUseCase
import com.salimisler.fieldposts.presantation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val initPostScreenUseCase: InitPostScreenUseCase
) : BaseViewModel() {
    private val _postsMSF = MutableStateFlow<List<PostUiModel>?>(null)
    val postSF = _postsMSF.asStateFlow()

    init {
        initScreen()
    }

    private fun initScreen() = viewModelScope.launch {
        val params = InitPostScreenUseCase.ParamsIn()
        initPostScreenUseCase.invoke(params).collectLatest {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    hideLoading()
                    _postsMSF.value = it.data?.posts
                }
                Resource.Status.LOADING -> {
                    showLoading()
                }
                Resource.Status.ERROR -> {
                    hideLoading()
                }
            }
        }
    }
}