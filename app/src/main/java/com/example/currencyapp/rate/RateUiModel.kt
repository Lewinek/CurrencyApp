package com.example.currencyapp.rate

import com.example.core_architecture.UiModel
import com.example.core_networking.Rate

data class RateUiModel(
    val showError: Boolean? = null,
    val rates: MutableList<Rate>? = null
) : UiModel() {
    override fun update(newModel: UiModel) = (newModel as RateUiModel).copy(
        rates = newModel.rates ?: rates
    )
}