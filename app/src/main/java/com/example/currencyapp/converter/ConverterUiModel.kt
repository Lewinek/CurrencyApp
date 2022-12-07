package com.example.currencyapp.converter

import com.example.core_architecture.UiModel
import com.example.core_networking.Currency

data class ConverterUiModel(
    val showError: Boolean? = null,
    val rates: MutableList<CurrencyDisplayable>? = null
) : UiModel() {
    override fun update(newModel: UiModel) = (newModel as ConverterUiModel).copy(
        rates = newModel.rates ?: rates
    )
}