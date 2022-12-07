package com.example.currencyapp.rate

import com.example.core_architecture.BaseFragment
import com.example.currencyapp.R
import com.example.currencyapp.RateAdapter
import com.example.currencyapp.databinding.FragmentRateBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConverterFragment :
    BaseFragment<ConverterUiModel, ConverterViewModel, FragmentRateBinding>(R.layout.fragment_rate) {
    override val viewModel: ConverterViewModel by viewModel()

    override fun initViews(binding: FragmentRateBinding) {
        binding.ratesRecyclerView.adapter = RateAdapter(
            onItemClick = {
                viewModel.getRatesByBaseCurrency(it)
            },
            onValueChange = {
                viewModel.calculateEquivalentToAmountBaseCurrency(it)
            }
        )
    }

    override fun render(model: ConverterUiModel) {
        model.rates?.let {
            (binding?.ratesRecyclerView?.adapter as RateAdapter).submitList(it)
            binding?.ratesRecyclerView?.scrollToPosition(0)
        }
    }
}