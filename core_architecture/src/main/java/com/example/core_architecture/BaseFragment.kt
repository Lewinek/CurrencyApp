package com.example.core_architecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<UM : UiModel, VM : BaseViewModel<UM>, B : ViewDataBinding>(
    @LayoutRes val layoutRes: Int
) : Fragment() {

    protected abstract val viewModel: VM
    var binding: B? = null

    protected abstract fun initViews(binding: B)
    protected abstract fun render(model: UM)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutRes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)
        binding?.let {
            initViews(it)
        }
        viewModel.run {
            uiLiveData.observe(viewLifecycleOwner) { render(it) }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}