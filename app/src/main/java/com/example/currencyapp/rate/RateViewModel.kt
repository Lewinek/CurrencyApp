package com.example.currencyapp.rate

import androidx.lifecycle.viewModelScope
import com.example.core_architecture.BaseViewModel
import com.example.core_networking.CurrencyRepository
import com.example.core_networking.ResultWrapper
import kotlinx.coroutines.launch

class RateViewModel(private val repository: CurrencyRepository) : BaseViewModel<RateUiModel>() {

    init {
        getRatesByBaseCurrency()
    }

    private fun getRatesByBaseCurrency() {
        viewModelScope.launch {
            viewModelScope.launch {
                uiState = when(val ratesResponse = repository.getRatesByBaseCurrency()){
                    is ResultWrapper.Success -> RateUiModel(rates = ratesResponse.value.toMutableList())
                    is ResultWrapper.GenericError -> RateUiModel(showError = true)
                    is ResultWrapper.NetworkError -> RateUiModel(showError = true)
                }
            }
        }
    }
}