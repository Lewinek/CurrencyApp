package com.example.currencyapp.converter

import androidx.lifecycle.viewModelScope
import com.example.core_architecture.BaseViewModel
import com.example.core_networking.CurrencyRepository
import com.example.core_networking.ResultWrapper
import com.example.currencyapp.Constants
import com.example.currencyapp.CurrencyCreator
import kotlinx.coroutines.launch
import java.math.BigDecimal

class ConverterViewModel(
    private val repository: CurrencyRepository,
    private val currencyCreator: CurrencyCreator
    ) : BaseViewModel<ConverterUiModel>() {

    init {
        getRatesByBaseCurrency(Constants.INITIAL_CURRENCY_VALUE_NAME)
    }

    fun getRatesByBaseCurrency(currencyName: String) {
        viewModelScope.launch {
            uiState = when (val currencyResponse = repository.getRatesByBaseCurrency(currencyName)) {
                is ResultWrapper.Success -> ConverterUiModel(
                    currencies = addBaseCurrencyToFirstItem(
                        currencyResponse.value
                            .map { CurrencyDisplayable(it) }
                            .toMutableList(),
                        currencyName
                    )
                )
                else -> ConverterUiModel(showError = true)
            }
        }
    }

    private fun addBaseCurrencyToFirstItem(
        currencies: MutableList<CurrencyDisplayable>,
        currencyName: String
    ): MutableList<CurrencyDisplayable> {
        currencies.add(0, currencyCreator.createBaseCurrency(currencyName))
        return currencies
    }

    fun calculateEquivalentToAmountBaseCurrency(baseCurrency: BigDecimal) {
        uiState =
            ConverterUiModel(currencies = uiState?.currencies
                ?.map { it.copy(convertedValue = it.value * baseCurrency) }
                ?.toMutableList())
    }
}