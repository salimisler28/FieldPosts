package com.salimisler.fieldposts.presantation.ui.screens.favs

import androidx.lifecycle.viewModelScope
import com.salimisler.fieldposts.core.Resource
import com.salimisler.fieldposts.domain.model.PostUiModel
import com.salimisler.fieldposts.domain.usecases.ListenFavsUseCase
import com.salimisler.fieldposts.presantation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavsViewModel @Inject constructor(
    private val listenFavsUseCase: ListenFavsUseCase
) : BaseViewModel() {
    private val _favsMSF = MutableStateFlow<List<PostUiModel>?>(null)
    val favsSF = _favsMSF.asStateFlow()

    init {
        listenFavs()
    }

    private fun listenFavs() = viewModelScope.launch {
        listenFavsUseCase.invoke(ListenFavsUseCase.ParamsIn())
            .collectLatest {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        hideLoading()
                        _favsMSF.value = it.data
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