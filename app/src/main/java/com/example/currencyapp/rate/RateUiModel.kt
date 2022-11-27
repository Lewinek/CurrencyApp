package com.example.currencyapp.rate

import com.example.core_architecture.UiModel

data class RateUiModel(
    private val isLoading: Boolean? = null
) : UiModel() {
    override fun update(newModel: UiModel) = (newModel as RateUiModel).copy()
}