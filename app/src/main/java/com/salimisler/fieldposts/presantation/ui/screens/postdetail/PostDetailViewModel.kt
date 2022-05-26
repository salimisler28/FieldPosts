package com.salimisler.fieldposts.presantation.ui.screens.postdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.salimisler.fieldposts.core.Resource
import com.salimisler.fieldposts.domain.model.PostDetailUiModel
import com.salimisler.fieldposts.domain.model.PostUiModel
import com.salimisler.fieldposts.domain.usecases.InitPostDetailUseCase
import com.salimisler.fieldposts.domain.usecases.ToggleFavUseCase
import com.salimisler.fieldposts.presantation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val initPostDetailUseCase: InitPostDetailUseCase,
    private val toggleFavUseCase: ToggleFavUseCase
) : BaseViewModel() {
    val postId = savedStateHandle.get<Int>("postId")

    private val _postDetailMSF = MutableStateFlow<PostDetailUiModel?>(null)
    val postDetailSF = _postDetailMSF.asStateFlow()

    init {
        initScreen()
    }

    private fun initScreen() = viewModelScope.launch {
        postId?.let {
            val params = InitPostDetailUseCase.ParamsIn(it)
            initPostDetailUseCase.invoke(params)
                .collectLatest {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            hideLoading()
                            _postDetailMSF.value = it.data?.postDetail
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



    fun toggleFav(postUiModel: PostUiModel) = viewModelScope.launch {
        val params = ToggleFavUseCase.ParamsIn(postUiModel)
        toggleFavUseCase.invoke(params).collectLatest {
            // here can add states for ui
        }
    }
}