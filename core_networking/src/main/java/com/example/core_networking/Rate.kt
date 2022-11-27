package com.example.core_networking

data class Rate(
    val name : String,
    val rate: Double,
    val isBaseCurrency: Boolean = false,
    val convertedValue: Double? = null
)
