package com.example.currencyapp

import com.example.core_architecture.UiModel

data class MainActivityUiModel(
    val isLoading: Boolean? = null,
) : UiModel() {
    override fun update(newModel: UiModel) = (newModel as MainActivityUiModel).copy()
}