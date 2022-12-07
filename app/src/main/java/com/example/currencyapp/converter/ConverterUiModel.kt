package com.example.currencyapp.converter

import com.example.core_architecture.UiModel

data class ConverterUiModel(
    val showError: Boolean? = null,
    val currencies: MutableList<CurrencyDisplayable>? = null
) : UiModel() {
    override fun update(newModel: UiModel) = (newModel as ConverterUiModel).copy(
        currencies = newModel.currencies ?: currencies
    )
}