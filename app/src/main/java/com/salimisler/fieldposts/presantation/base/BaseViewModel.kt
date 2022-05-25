package com.salimisler.fieldposts.presantation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {
    /**
     * Base loading flow
     * */
    private val _baseLoadingMSF = MutableStateFlow(true)
    val baseLoadingSF: StateFlow<Boolean> = _baseLoadingMSF

    /**
     * Base error flow
     * */
    private val _baseErrorMSF = MutableStateFlow<Exception?>(null)
    val baseErrorSF: StateFlow<Exception?> = _baseErrorMSF

    fun showLoading() { _baseLoadingMSF.value = true }
    fun hideLoading() { _baseLoadingMSF.value = false }
}