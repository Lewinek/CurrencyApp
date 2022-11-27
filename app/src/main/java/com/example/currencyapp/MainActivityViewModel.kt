package com.example.currencyapp

import androidx.lifecycle.viewModelScope
import com.example.core_architecture.BaseViewModel
import com.example.core_networking.CurrencyRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: CurrencyRepository) :
    BaseViewModel<MainActivityUiModel>() {

    init {
        getRates()
    }

    private fun getRates() {
        viewModelScope.launch {
            val rates = repository.getRatesByBaseCurrency()
        }

    }
}