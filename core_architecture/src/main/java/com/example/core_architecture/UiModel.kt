package com.example.core_architecture

abstract class UiModel {
    abstract fun update(newModel: UiModel): UiModel
}