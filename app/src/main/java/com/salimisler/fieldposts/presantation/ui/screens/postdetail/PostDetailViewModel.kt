package com.salimisler.fieldposts.presantation.ui.screens.postdetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.salimisler.fieldposts.presantation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): BaseViewModel() {
    val postId = savedStateHandle.get<Int>("postId")

    init {

    }
}