package com.salimisler.fieldposts.presantation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding, VM: BaseViewModel> constructor(
    @LayoutRes layoutRes: Int
): Fragment(layoutRes) {
    private var _binding: VB? = null
    protected val binding: VB
        get() = requireNotNull(_binding)
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}