package com.example.currencyapp.rate

import androidx.lifecycle.viewModelScope
import com.example.core_architecture.BaseViewModel
import com.example.core_networking.CurrencyRepository
import com.example.core_networking.Currency
import com.example.core_networking.ResultWrapper
import com.example.currencyapp.Constants
import kotlinx.coroutines.launch
import java.math.BigDecimal

class RateViewModel(private val repository: CurrencyRepository) : BaseViewModel<RateUiModel>() {

    init {
        getRatesByBaseCurrency(Constants.INITIAL_CURRENCY_VALUE_NAME)
    }

    fun getRatesByBaseCurrency(currencyName: String) {
        viewModelScope.launch {
            uiState = when (val ratesResponse = repository.getRatesByBaseCurrency(currencyName)) {
                is ResultWrapper.Success -> RateUiModel(
                    rates = addBaseCurrencyToFirstItem(
                        ratesResponse.value.toMutableList(),
                        currencyName
                    )
                )
                is ResultWrapper.GenericError -> RateUiModel(showError = true)
                is ResultWrapper.NetworkError -> RateUiModel(showError = true)
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
            RateUiModel(rates = uiState?.rates
                ?.map { it.copy(convertedValue = it.value * baseCurrency) }
                ?.toMutableList())
    }
}