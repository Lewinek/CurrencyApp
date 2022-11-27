package com.example.currencyapp.rate

import androidx.lifecycle.viewModelScope
import com.example.core_architecture.BaseViewModel
import com.example.core_networking.CurrencyRepository
import kotlinx.coroutines.launch

class RateViewModel(private val repository: CurrencyRepository) : BaseViewModel<RateUiModel>() {

    init {
        getRatesByBaseCurrency()
    }

    private fun getRatesByBaseCurrency() {
        viewModelScope.launch {
            val ratesResponse = repository.getRatesByBaseCurrency()
        }
    }
}