package com.example.core_networking

data class CurrencyResponse(
    val baseCurrency: String,
    val rates: Map<String, Double>
) {
   val convertedRates: List<Currency> = rates.map { Currency(it.key, it.value.toBigDecimal()) }
}