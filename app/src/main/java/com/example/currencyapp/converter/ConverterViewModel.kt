package com.example.currencyapp.converter

import androidx.lifecycle.viewModelScope
import com.example.core_architecture.BaseViewModel
import com.example.core_networking.CurrencyRepository
import com.example.core_networking.Currency
import com.example.core_networking.ResultWrapper
import com.example.currencyapp.Constants
import kotlinx.coroutines.launch
import java.math.BigDecimal

class ConverterViewModel(private val repository: CurrencyRepository) : BaseViewModel<ConverterUiModel>() {

    init {
        getRatesByBaseCurrency(Constants.INITIAL_CURRENCY_VALUE_NAME)
    }

    fun getRatesByBaseCurrency(currencyName: String) {
        viewModelScope.launch {
            uiState = when (val ratesResponse = repository.getRatesByBaseCurrency(currencyName)) {
                is ResultWrapper.Success -> ConverterUiModel(
                    rates = addBaseCurrencyToFirstItem(
                        ratesResponse.value.toMutableList(),
                        currencyName
                    )
                )
                is ResultWrapper.GenericError -> ConverterUiModel(showError = true)
                is ResultWrapper.NetworkError -> ConverterUiModel(showError = true)
            }
        }
    }

    private fun createBaseCurrency(currencyName: String) =
        Currency(currencyName, 1.toBigDecimal(), true, 1.toBigDecimal())

    private fun addBaseCurrencyToFirstItem(
        currencies: MutableList<Currency>,
        currencyName: String
    ): MutableList<Currency> {
        currencies.add(0, createBaseCurrency(currencyName))
        return currencies
    }

    fun calculateEquivalentToAmountBaseCurrency(baseCurrency: BigDecimal) {
        uiState =
            ConverterUiModel(rates = uiState?.rates
                ?.map { it.copy(convertedValue = it.value * baseCurrency) }
                ?.toMutableList())
    }
}