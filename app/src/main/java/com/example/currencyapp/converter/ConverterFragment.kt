package com.example.currencyapp.converter

import com.example.core_architecture.BaseFragment
import com.example.currencyapp.ConverterAdapter
import com.example.currencyapp.R
import com.example.currencyapp.databinding.FragmentConverterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConverterFragment :
    BaseFragment<ConverterUiModel, ConverterViewModel, FragmentConverterBinding>(R.layout.fragment_converter) {
    override val viewModel: ConverterViewModel by viewModel()

    override fun initViews(binding: FragmentConverterBinding) {
        binding.converterRecyclerView.adapter = ConverterAdapter(
            onItemClick = {
                viewModel.getRatesByBaseCurrency(it)
            },
            onValueChange = {
                viewModel.calculateEquivalentToAmountBaseCurrency(it)
            }
        )
    }

    override fun render(model: ConverterUiModel) {
        model.currencies?.let {
            (binding?.converterRecyclerView?.adapter as ConverterAdapter).submitList(it)
            binding?.converterRecyclerView?.scrollToPosition(0)
        }
    }
}