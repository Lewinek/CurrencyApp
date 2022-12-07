package com.example.core_networking

data class CurrencyResponse(
    val baseCurrency: String,
    val rates: Map<String, Double>
) {
   val convertedRates: List<CurrencyRemote> = rates.map { CurrencyRemote(it.key, it.value.toBigDecimal()) }
}