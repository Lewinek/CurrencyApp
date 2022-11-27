package com.example.core_networking

data class CurrencyResponse(
    val baseCurrency: String,
    val rates: Map<String, Double>
) {
   val convertedRates: List<Rate> = rates.map { Rate(it.key, it.value) }
}