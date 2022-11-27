package com.example.currencyapp.rate

import com.example.core_architecture.BaseFragment
import com.example.currencyapp.R
import com.example.currencyapp.databinding.FragmentRateBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RateFragment: BaseFragment<RateUiModel, RateViewModel, FragmentRateBinding>(R.layout.fragment_rate) {
    override val viewModel: RateViewModel by viewModel()

    override fun initViews(binding: FragmentRateBinding) {
    }

    override fun render(model: RateUiModel) {
    }
}