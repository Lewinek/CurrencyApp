package com.example.currencyapp.rate

import androidx.lifecycle.viewModelScope
import com.example.core_architecture.BaseViewModel
import com.example.core_networking.CurrencyRepository
import com.example.core_networking.Rate
import com.example.core_networking.ResultWrapper
import kotlinx.coroutines.launch

class RateViewModel(private val repository: CurrencyRepository) : BaseViewModel<RateUiModel>() {

    init {
        getRatesByBaseCurrency("GBP")
    }

    private fun getRatesByBaseCurrency(name: String) {

        viewModelScope.launch {
            viewModelScope.launch {
                uiState = when(val ratesResponse = repository.getRatesByBaseCurrency()){
                    is ResultWrapper.Success -> {
                        val list = ratesResponse.value.toMutableList()
                        list.add(0, saveBaseCurrency(name))
                        RateUiModel(rates = list)
                    }
                    is ResultWrapper.GenericError -> RateUiModel(showError = true)
                    is ResultWrapper.NetworkError -> RateUiModel(showError = true)
                }
            }
        }
    }

    private fun saveBaseCurrency(name: String): Rate {
        return Rate(name, 1.0)
    }
}