package com.example.core_architecture

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<UM : UiModel> : ViewModel() {

    private val _uiLiveData = MutableLiveData<UM>()

    protected var uiState: UM?
        get() = _uiLiveData.value
        set(value) = when (value) {
            null -> _uiLiveData.value = value
            else -> _uiLiveData.value = _uiLiveData.value?.update(value) as? UM ?: value
        }
}